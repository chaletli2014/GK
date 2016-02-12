; (function() {
    if (top != window) {
        var insertStyle = function(rules) {
            var style = document.createElement("style");
            style.type = 'text/css';
            document.getElementsByTagName("head")[0].appendChild(style);
            if (rules) {
                if (style.styleSheet) {
                    style.styleSheet.cssText = rules;
                } else {
                    style.appendChild(document.createTextNode(rules));
                }
            }
            return style.sheet || style;
        };
        var insertJS = function(src, hdl) {
            var script = document.createElement("script");
            script.type = "text/javascript";
            script.src = src;
            document.getElementsByTagName("head")[0].appendChild(script);
        };
        var setIfrHeight = function() {
            try {
                if (!window.xMsg) {
                    return;
                }
                if (!xMsg.isInit) {
                    xMsg.init("qcloud");
                    xMsg.setTarget("open", window.top);
                    xMsg.isInit = true;
                }
                if (window.parent != window.top) {
                    if (parent.setFrameHeight) {
                        xMsg.call("open", "setFrameHeight", {
                            height: 800
                        },
                        function(ret) {});
                        return;
                    }
                }
                if (window.qcloud) {
                    if (window.qcloud.loadBalanceManageV2 || window.qcloud.loadBalanceViewV2 || window.qcloud.ticket || window.qcloud.mobileSpeedUp) {
                        xMsg.call("open", "setFrameHeight", {
                            height: 800
                        },
                        function(ret) {});
                        return;
                    }
                }
                var height = document.body.clientHeight;
                if (Math.abs(height - ifrHeight) > 10) {
                    ifrHeight = height;
                    xMsg.call("open", "setFrameHeight", {
                        height: ifrHeight
                    },
                    function(ret) {});
                }
            } catch(e) {}
        }
        var domain = '',
        host = '',
        oldDomain;
        try {
//            domain = window.top.document.domain host = window.top.location.host;
            domain = window.top.document.domain;
            host = window.top.location.host;
        } catch(e) {
            oldDomain = document.domain;
            if (/\.qcloud\.com$/i.test(oldDomain)) {
                document.domain = 'qcloud.com';
                try {
                    domain = window.top.document.domain;
                    host = window.top.location.host;
                } catch(e) {} finally {
                    document.domain = oldDomain;
                }
            }
        }
        if (!/qcloud\.com$/i.test(domain)) {
            insertStyle('html{background:none}.nav_area_1,.head_v2,mod_customer_service,.foot_v2{display:none;}.j_home{visibility:hidden}.mod_form{background:#fff}');
            insertJS("http://qzonestyle.gtimg.cn/open/operate/mlib/widget/x-msg.js");
            var ifrHeight = 0,
            isInit;
            window.iframeInit = function() {
                setIfrHeight();
                if (isInit) {
                    return;
                }
                var msgEl = document.getElementById("iframe_msg_span");
                var bubEl = document.getElementById("important_msg_tip");
                var btnCart = document.getElementById("nav_btn_shopcart");
                var btnBuy = document.getElementById("nav_btn_buy");
                var serviceDiv = document.getElementById("foot_customer_service");
                if (msgEl) {
                    msgEl.style.display = "";
                    if (bubEl) {
                        bubEl.style.left = ( - 70) + 'px';
                        bubEl.style.top = 28 + 'px';
                        msgEl.appendChild(bubEl);
                    }
                    isInit = true
                }
                if (btnCart) {
                    btnCart.removeAttribute("target");
                    isInit = true
                }
                if (btnBuy) {
                    btnBuy.removeAttribute("target");
                    isInit = true
                }
                if (serviceDiv) {
                    serviceDiv.parentNode.removeChild(serviceDiv);
                    isInit = true
                }
            }
            setInterval(iframeInit, 100);
            if (/passport\.qcloud\.com/i.test(window.location.host)) {
                setTimeout(function() {
                    if (window.xMsg) {
                        xMsg.call("open", "sessionEnd");
                    } else {
                        setTimeout(arguments.callee, 60);
                    }
                },
                30);
            }
        } else if (/.*console.*\.qcloud\.com$/i.test(host)) {
            insertStyle('.embed-in-newmc .head-tool, .embed-in-newmc .head-v3, .embed-in-newmc .foot-v3, .embed-in-newmc .tc-footer, .embed-in-newmc .footer-menu, .embed-in-newmc .head_manage_v2{display: none!important;} html.embed-in-newmc { background: none;}');
            document.body.className += ' embed-in-newmc';
            document.documentElement.className += ' embed-in-newmc';
            window.qcloud = window.qcloud || {};
            qcloud.isFromNewMc = true;
            qcloud.FROM_QUERY_STRING = "?from=new_mc";
            qcloud.FROM_PARAM_STRING = "&from=new_mc";
        }
    }
})();
/*  |xGv00|5fe11e4afe30b23cff8e79168edfb7e1 */