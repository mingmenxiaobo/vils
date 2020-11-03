/**
 * __author__ zhangyunliang
 **/



let canvas = document.querySelectorAll('#canvas_box')[0];
let context = canvas.getContext('2d');
let scale = 1.00; //缩放倍率
let img = new Image();
let bigDiv = document.getElementById("bigDiv");

//todo 鼠标滚轮
let addMouseWheel = function (dom, hander) {
    dom.addEventListener('touchmove', function (e) {
        //toastr.info(e.scale);
        if (event.touches.length > 1) {
            if (e.scale > 1) {
                e.delta = e.scale + 1;
            } else {
                e.delta = e.scale - 1;
            }
            hander(e);
        }
    });
    //兼容完整处理 通过浏览器判断
    let browser = window.navigator.userAgent.toLowerCase().indexOf('firefox');
    if (browser != -1) {
        //处理火狐滚轮事件
        dom.addEventListener('DOMMouseScroll', function (e) {
            //获取当前鼠标的滚动情况
            e.delta = -(e.detail || 0) / 3;
            hander(e);

            let oEvent = e || event;
            event.wheelDelta = event.wheelDelta ? event.wheelDelta : (event.deltalY * (-40));
            //上下滚轮动作判断
            if (oEvent.detail < 0) {
                console.log('放大');
            } else {
                console.log('缩小');
            }
        })
    } else {
        //其他浏览器
        dom.onmousewheel = function (e) {
            e.delta = e.wheelDelta / 120;
            hander(e);

            let oEvent = e || event;
            event.wheelDelta = event.wheelDelta ? event.wheelDelta : (event.deltalY * (-40));  //获取当前鼠标的滚动情况
            //上下滚轮动作判断
            if (oEvent.wheelDelta > 0) {
                console.log('放大');
            } else {
                console.log('缩小');
            }
        }
    }
};

//todo 判断是否是手机端
function isPhone() {
    let u = navigator.userAgent;
    let isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    let isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    if (isAndroid || isiOS) {
        return true;
    } else {
        return false;
    }
}

//todo 加载地图
let load_template = function () {
    document.title = Template_Info.template_name;
    canvas.width = Template_Info.width;
    canvas.height = Template_Info.height;
    $(".control_box").css({
        'position': 'absolute',
        'left': ($(window).get(0).innerWidth - Template_Info.width) / 2,
        'top': ($(window).get(0).innerHeight - Template_Info.height) / 2,
        'width': Template_Info.width + "px",
        'height': Template_Info.height + "px"
    });
    $("#canvas_box").css({
        'margin-left': ($(window).get(0).innerWidth - Template_Info.width) / 2,
        'margin-top': ($(window).get(0).innerHeight - Template_Info.height) / 2
    });


    let oldX = 0, oldY = 0;
    let isMove = false;
    let spanLeft = 0, spanTop = 0;

    $(window).resize(function () {
        canvas.width = Template_Info.width;
        canvas.height = Template_Info.height;
        $(".control_box").css({
            'position': 'absolute',
            'left': ($(window).get(0).innerWidth - Template_Info.width) / 2,
            'top': ($(window).get(0).innerHeight - Template_Info.height) / 2,
            'width': Template_Info.width + "px",
            'height': Template_Info.height + "px"
        });
        $("#canvas_box").css({
            'margin-left': ($(window).get(0).innerWidth - Template_Info.width) / 2,
            'margin-top': ($(window).get(0).innerHeight - Template_Info.height) / 2
        });
        show();
    });
    img.src = "/"+Template_Info.base_map;
    if (isPhone()) {
        Mobile_move();
    }

    img.onload = function () {
        show();
        //鼠标移动事件
        addMouseWheel(canvas, function (e) {
            let temp = e.delta > 0 ? 0.1 : -0.1;
            scale += temp;
            //缩放极限判断
            scale = scale < 0.3 ? 0.3 : scale;
            scale = scale > 2 ? 2 : scale;
            show();
        });
        canvas.onmousedown = function (e) {
            oldX = e.clientX;
            oldY = e.clientY;
            isMove = true;
        };
        canvas.onmousemove = function (e) {
            if (isMove) {
                document.body.style.background = 'black';
                canvas.style.cursor = 'move';
                let currentX = e.clientX;
                let currentY = e.clientY;
                //计算移动的距离
                let spanX = currentX - oldX;
                let spanY = currentY - oldY;
                spanLeft += spanX;
                spanTop += spanY;
                context.translate(spanX, spanY);
                show();
                //记录当前结果
                oldX = currentX;
                oldY = currentY;
            }
        };
        canvas.onmouseup = function () {
            canvas.style.cursor = 'default';
            oldX = oldY = 0;
            isMove = false;
        };
        canvas.onmouseleave = function () {
            oldX = oldY = 0;
            isMove = false;
        };
    };

    //todo 画图
    function show() {
        let cWidth = canvas.width;
        let cHeight = canvas.height;
        let width = img.width * scale;
        let height = img.height * scale;
        //居中显示
        let left = (cWidth - width) / 2;
        let top = (cHeight - height) / 2;

        if (scale == 1) {
            $("#bigDiv").css({'width': img.width, 'height': img.height});
            $("#bigDiv").css({'left': left, 'top': top});
        } else {
            $("#bigDiv").css({'width': img.width * scale, 'height': img.height * scale});
            $("#bigDiv").css({'left': left, 'top': top});
        }
        //清除画布
        context.clearRect(-spanLeft, -spanTop, canvas.width, canvas.height);
        context.drawImage(img, left, top, width, height);
        bindDom(spanLeft, spanTop);
    }
};

function Refresh_List(doms) {
    $('#code_table>tbody').html("");
    let append_html = '';
    for (let k = 0; k < doms.length; k++) {
        //todo 更新控件列表
        append_html += '<tr>\n' +
            '<td>' + doms[k].control_name + '</td>\n' +
            '<td>' + doms[k].qrcode + '</td>\n' +
            '<td class="edit">\n' +
            '<a href="javascript:void(0);" data-toggle="modal" data-target="#delete_control_Model">\n' +
            '<img src="/mould/img/pic/edit.png" class="edit_img" data-toggle="modal" data-target="#editControlModel" onclick="edit_control(' + "'" + doms[k].control_name + "'" + ',this)">\n' +
            '</a>\n' +
            '</td>\n' +
            '</tr>';

    }
    $('#code_table>tbody').append(append_html);
}

//todo 拖拽复制、添加隐藏域(绑定旋转缩放事件)
function Add_Controls(doms) {
    //console.log(doms);
    //todo 保持呼吸灯效果一致
    $(".nodes").css({'animation-name': 'test', '-webkit-animation-name': 'test'});
    setTimeout(function () {
        $(".nodes").css({'animation-name': 'breathe', '-webkit-animation-name': 'breathe'});
    }, 100);
    $('#code_table>tbody').html("");
    for (let k = 0; k < doms.length; k++) {
        let move_obj = document.createElement('div');
        move_obj.id = doms[k].control_name;
        let shape = JSON.parse(doms[k].control_shape);
        move_obj.style.left = shape.x + "px";
        move_obj.style.top = shape.y + "px";

        if(typeof shape.width == 'string'){
            move_obj.style.width = shape.width;
        }else{
            move_obj.style.width = shape.width + "px";
        }
        if(typeof shape.height =='string'){
            move_obj.style.height = shape.height;
        }else{
            move_obj.style.height = shape.height + "px";
        }

        if (doms[k].qrcode == 'undefined' || doms[k].qrcode == undefined) {
            doms[k].qrcode = '暂未绑定控件';
        }
        if (doms[k].viewName == 'undefined' || doms[k].viewName == undefined) {
            doms[k].viewName = '暂未绑定控件';
        }
        if (shape.control_type == "circle") {
            move_obj.className = 'nodes';
        } else {
            move_obj.className = 'nodes no-radius';
        }


        //todo 将拖拽进来的控件节点存入 hidden input
        let _input = document.createElement("input");
        _input.type = 'hidden';
        _input.name = move_obj.id;
        _input.className = 'hide_input';
        let arr2 = JSON.stringify([{
            x: shape.x,
            y: shape.y,
            control_name: move_obj.id,
            width: move_obj.style.width,
            height: move_obj.style.height
        }]);
        _input.value = arr2;
        move_obj.appendChild(_input);

        //todo 更新控件列表
        let append_html = '<tr>\n' +
            '<td>' + move_obj.id + '</td>\n' +
            '<td>' + doms[k].qrcode + '</td>\n' +
            '<td class="edit">\n' +
            '<a href="javascript:void(0);" data-toggle="modal" data-target="#delete_control_Model">\n' +
            '<img src="/mould/img/pic/edit.png" class="edit_img" data-toggle="modal" data-target="#editControlModel" onclick="edit_control(' + "'" + move_obj.id + "'" + ',this)">\n' +
            '</a>\n' +
            '</td>\n' +
            '</tr>';
        $('#code_table>tbody').append(append_html);
        // $("#" + move_obj.id + " input").remove();
        bigDiv.append(move_obj);
        $("#" + move_obj.id ).css("transform", "rotate(" + shape.control_angle + "deg)");
        move_obj.onmouseenter = function () {
            let tips = document.createElement('div');
            tips.innerHTML = "控件信息(" + move_obj.id + ")<span class='arrow'></span>";
            tips.className = "tooltips " + move_obj.id;
            bigDiv.append(tips);
            $("." + move_obj.id).css({
                'display': 'flex',
                'left': Math.abs(parseInt(move_obj.style.left) - parseInt(move_obj.style.width)) + "px",
                'top': Math.abs(parseInt(move_obj.style.top) - parseInt(move_obj.style.height)) + "px"
            });
        };
        move_obj.onmouseleave = function () {
            $("." + move_obj.id).remove();
        };
    }
}

//todo 绑定控件同时移动放大缩小
function bindDom(oldX, oldY) {
    let arr = [];
    $(".hide_input").each(function (i, item) {
        if ($(this).val()) {
            arr.push(JSON.parse($(this).val()));
        }
    });
    //console.log(scale);
    if (arr.length > 0) {
        //console.log(margin_left, margin_top);
        for (let k = 0; k < arr.length; k++) {
            let width = parseInt(arr[k][0]['width']);
            let height = parseInt(arr[k][0]['height']);
            let x = arr[k][0]['x'];
            let y = arr[k][0]['y'];
            document.getElementById(arr[k][0]['control_name']).style.width = width * scale + 'px';
            document.getElementById(arr[k][0]['control_name']).style.height = height * scale + 'px';
            document.getElementById(arr[k][0]['control_name']).style.left = scale * x + oldX + 'px';
            document.getElementById(arr[k][0]['control_name']).style.top = scale * y + oldY + 'px';
        }
    }
}

//todo 移动端操作
function Mobile_move() {
    //todo 移动端参数事件
    let translateX = 0;
    let translateY = 0;
    let scaleRatio = 1;
    let scaleOrigin = {
        x: 0,
        y: 0
    };
    let preTouchesClientx1y1x2y2 = [];
    let originHaveSet = false;
    let preTouchPosition = {};
    const recordPreTouchPosition = (touch) => {
        preTouchPosition = {
            x: touch.clientX,
            y: touch.clientY
        };
    };
    const getStyle = (target, style) => {
        let styles = window.getComputedStyle(target, null);
        return styles.getPropertyValue(style);
    };
    const getTranslate = (target) => {
        let matrix = getStyle(target, 'transform');
        let nums = matrix.substring(7, matrix.length - 1).split(', ');
        let left = parseInt(nums[4]) || 0;
        let top = parseInt(nums[5]) || 0;
        return {
            left: left,
            top: top
        }
    };
    const relativeCoordinate = (x, y, rect) => {
        let cx = (x - rect.left) / scaleRatio;
        let cy = (y - rect.top) / scaleRatio;
        return {
            x: cx,
            y: cy
        };
    };
    const setStyle = (key, value) => {
        canvas.style[key] = value;
    };

    const setNodeStyle = (key, value) => {
        $(".nodes").each(function (index, item) {
            item.style[key] = value;
        });
    };

    canvas.addEventListener('touchstart', e => {
        let touches = e.touches;
        if (touches.length > 1) {
            let one = touches['0'];
            let two = touches['1'];
            preTouchesClientx1y1x2y2 = [one.clientX, one.clientY, two.clientX, two.clientY];
            originHaveSet = false;
        }
        recordPreTouchPosition(touches['0']);
    });

    canvas.addEventListener('touchmove', e => {
        let touches = e.touches;
        if (touches.length === 1) {
            let oneTouch = touches['0'];
            let translated = getTranslate(oneTouch.target);
            translateX = oneTouch.clientX - preTouchPosition.x + translated.left;
            translateY = oneTouch.clientY - preTouchPosition.y + translated.top;
            let matrix = `matrix(${scaleRatio}, 0, 0, ${scaleRatio}, ${translateX}, ${translateY})`;
            setStyle('transform', matrix);
            //todo 将移动缩放距离赋给控件
            setNodeStyle('transform', matrix);

            recordPreTouchPosition(oneTouch);
        } else {
            return;
            let one = touches['0'];
            let two = touches['1'];
            const distance = (x1, y1, x2, y2) => {
                let a = x1 - x2;
                let b = y1 - y2;
                return Math.sqrt(a * a + b * b);
            };
            scaleRatio = distance(one.clientX, one.clientY, two.clientX, two.clientY) / distance(...preTouchesClientx1y1x2y2) * scaleRatio || 1;
            if (!originHaveSet) {
                originHaveSet = true;
                // 移动视线中心
                let origin = relativeCoordinate((one.clientX + two.clientX) / 2, (one.clientY + two.clientY) / 2, canvas.getBoundingClientRect());
                // 修正视野变化带来的平移量
                translateX = (scaleRatio - 1) * (origin.x - scaleOrigin.x) + translateX;
                translateY = (scaleRatio - 1) * (origin.y - scaleOrigin.y) + translateY;
                setStyle('transform-origin', `${origin.x}px ${origin.y}px`);
                //todo 将移动缩放距离赋给控件
                setNodeStyle('transform-origin', `${origin.x}px ${origin.y}px`);

                scaleOrigin = origin;
            }
            let matrix = `matrix(${scaleRatio}, 0, 0, ${scaleRatio}, ${translateX}, ${translateY})`;
            setStyle('transform', matrix);
            //todo 将移动缩放距离赋给控件
            setNodeStyle('transform', matrix);

            preTouchesClientx1y1x2y2 = [one.clientX, one.clientY, two.clientX, two.clientY];
        }
        e.preventDefault();
    });

    // 触摸点离开时更新最后位置
    canvas.addEventListener('touchend', e => {
        let touches = e.touches;
        if (touches.length === 1) {
            recordPreTouchPosition(touches['0']);
        }
    });
    //todo 控件操作结束touch事件
    $(".nodes").each(function (index, item) {
        document.getElementById(item.id).addEventListener('touchend', e => {
            let touches = e.touches;
            if (touches.length === 1) {
                recordPreTouchPosition(touches['0']);
            }
        });
        document.getElementById(item.id).addEventListener('touchcancel', e => {
            let touches = e.touches;
            if (touches.length === 1) {
                recordPreTouchPosition(touches['0']);
            }
        });
    });

    canvas.addEventListener('touchcancel', e => {
        let touches = e.touches;
        if (touches.length === 1) {
            recordPreTouchPosition(touches['0']);
        }
    });
    //todo 浏览器兼容
    let passiveSupport = false;
    try {
        let option = Object.defineProperty({}, 'passive', {
            get: () => {
                console.log('support');
                passiveSupport = true;
            }
        });
        window.addEventListener('passivetest', null, option);
    } catch (err) {
    }
    // 仅针对无 scroll 页面的写法
    // 现阶段无法实现兼容 scroll 与阻止默认的浏览器自定义下拉事件（如微信）
    document.body.addEventListener('touchmove', (e) => {
        e.preventDefault();
    }, passiveSupport ? {
        passive: false
    } : false);
}