(function flexible(window, document) {

    // // 网易的做法
    // var deviceWidth = document.documentElement.clientWidth;
    // if(deviceWidth > 750) deviceWidth = 750;
    // document.documentElement.style.fontSize = deviceWidth / 7.5 + 'px';

    // return


    //淘宝的做法

    //var scale = 1 / window.devicePixelRatio;

    //document.querySelector('meta[name="viewport"]').setAttribute('content','width=device-width,initial-scale=' + scale + ', maximum-scale=' + scale + ', minimum-scale=' + scale + ', user-scalable=no');

    var docEl = document.documentElement;
    var dpr = window.devicePixelRatio || 1;

    // adjust body font size
    function setBodyFontSize() {
        if (document.body) {
            document.body.style.fontSize = (12 * dpr) + 'px';
        } else {
            document.addEventListener('DOMContentLoaded', setBodyFontSize);
        }
    }

    setBodyFontSize();

    // set 1rem = viewWidth / 10
    function setRemUnit() {
        if (docEl.clientWidth > 750) {
            docEl.style.fontSize = '100px';
            return
        }
        if (docEl.clientWidth < 320) {
            docEl.style.fontSize = '42.666666px';
            return
        }
        var rem = docEl.clientWidth / 7.5;
        docEl.style.fontSize = rem + 'px';
    }

    setRemUnit();

    // reset rem unit on page resize
    window.addEventListener('resize', setRemUnit);
    window.addEventListener('pageshow', function (e) {
        if (e.persisted) {

            setRemUnit();
        }
    });

    // detect 0.5px supports
    if (dpr >= 2) {
        var fakeBody = document.createElement('body');
        var testElement = document.createElement('div');
        testElement.style.border = '.5px solid transparent';
        fakeBody.appendChild(testElement);
        docEl.appendChild(fakeBody);
        if (testElement.offsetHeight === 1) {
            docEl.classList.add('hairlines');
        }
        docEl.removeChild(fakeBody);
    }

}(window, document))
