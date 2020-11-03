/**
 * __author__ zhangyunliang
 **/



let canvas = document.querySelectorAll('#canvas_box')[0];
let context = canvas.getContext('2d');
let scale = 1.00; //缩放倍率
let img = new Image();
let bigDiv = document.getElementById("bigDiv");
let canvass = document.getElementById("canvass");

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
    img.src = "/" + Template_Info.base_map;
    // let count = document.createElement('div');
    // count.innerHTML = "<span class='arrow'>1111</span>";
    // count.className = "tooltips " + move_obj.id;

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

//todo 拖拽复制、添加隐藏域(绑定旋转缩放事件)
function Add_Controls(doms) {
    let red=0;
    let yellow=0;
    let greed=0;
    for (let k = 0; k < doms.length; k++) {
        let move_obj = document.createElement('div');
        move_obj.id = doms[k].control_name;
        move_obj.idle=doms[k].idle;
        move_obj.depart=doms[k].depart;
        let shape = JSON.parse(doms[k].control_shape);
        move_obj.style.left = shape.x + "px";
        move_obj.style.top = shape.y + "px";
        move_obj.style.width = shape.width + "px";
        move_obj.style.height = shape.height + "px";

        if (shape.control_type == "circle") {
            move_obj.className = 'nodes';
        } else {
            move_obj.className = 'nodes no-radius';
        }
        if(move_obj.idle>0){
            //已使用
            move_obj.className = 'nodes controllerred';
            red++;
        }else{
            if(move_obj.depart>0){
                //离开
                move_obj.className='nodes no-radius controlleryellow';
                yellow++;
            }else{
                //未使用
                move_obj.className = 'nodes no-radius controllergreed';
                greed++;
            }
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
        bigDiv.append(move_obj);
        //todo 添加显示信息
        let info_div = document.createElement("div");
        info_div.className = 'infos';
        info_div.style.width = shape.width + "px";
        info_div.style.height = shape.height + "px";
        info_div.style.opacity = '0';
        info_div.innerHTML = '<div class="left">'+doms[k].viewName+'</div><div class="right">'+doms[k].viewCode+'</div>';
        move_obj.appendChild(info_div);
        if(Template_Info.template_type == 'JG'){
            if(doms[k].u_size){
                switch(doms[k].u_size){
                    case '1':
                        $("#" + move_obj.id ).addClass('u_size1');
                        break;
                    case '2':
                        $("#" + move_obj.id ).addClass('u_size2');
                        break;
                    case '3':
                        $("#" + move_obj.id ).addClass('u_size3');
                        break;
                    case '4':
                        $("#" + move_obj.id ).addClass('u_size4');
                        break;
                }
            }
        }
        //todo 给控件绑定点击事件
        $("#" + move_obj.id ).find('.infos').on('click',function () {
            $("#controlInfoModel").addClass('show').css('display', 'block');
            $("#btn1").attr('data-code',doms[k].dqrCode);
            $("#btn2").attr('data-code',doms[k].dqrCode);
            $("#btn3").attr('data-code',doms[k].dqrCode);
            $("#btn4").attr('data-code',doms[k].dqrCode);
        });
        $("#" + move_obj.id ).css("transform", "rotate(" + shape.control_angle + "deg)");
        $("#" + move_obj.id ).removeClass('nodes').addClass('nodes_white');
        move_obj.onmouseenter = function () {
            if(doms[k].u_size){
                $("#" + move_obj.id ).find('.infos').css('opacity','1');
            }else{
                let tips = document.createElement('div');
                if(Template_Info.template_type == 'FJ'){
                    console.log(doms);
                    let codes = doms[0].device_name.split(';');
                    console.log(codes);
                    tips.innerHTML = "设备编码(" + doms[k].device_name + ")<span class='arrow'></span>";
                }else{
                    tips.innerHTML = "设备编码(" + doms[k].device_name + ")<span class='arrow'></span>";
                }
                tips.className = "tooltips " + move_obj.id;
                bigDiv.append(tips);
                $("." + move_obj.id).css({
                    'display': 'flex',
                    'left': Math.abs(parseInt(move_obj.style.left) - parseInt(move_obj.style.width)) + "px",
                    'top': Math.abs(parseInt(move_obj.style.top) - parseInt(move_obj.style.height)) + "px"
                });
            }
        };
        move_obj.onmouseleave = function () {
            if(doms[k].u_size){
                $("#" + move_obj.id ).find('.infos').css('opacity','0');
            }else{
                $("." + move_obj.id).remove();
            }
        };
    }
    //todo  将统计结果写入地图上
    let count= document.createElement('div');
    count.innerHTML='<div class="backcolor1"></div><div class="backcss">Occupied:</div><div class="backcss">'+red+'</div><div class="backcolor2"></div><div class="backcss">Away:</div><div class="backcss">'+yellow+'</div><div class="backcolor3"></div><div class="backcss">Available:</div><div class="backcss">'+greed+'</div>';
    count.className='count';
    canvass.append(count);
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
            document.getElementById(arr[k][0]['control_name']).childNodes[1].style.width = width * scale + 'px';
            document.getElementById(arr[k][0]['control_name']).childNodes[1].style.height = height * scale + 'px';
            document.getElementById(arr[k][0]['control_name']).childNodes[1].style.fontSize = 10 * scale +"px";
            if(scale < 0.5){
                document.getElementById(arr[k][0]['control_name']).childNodes[1].style.display = 'none';
            }else{
                document.getElementById(arr[k][0]['control_name']).childNodes[1].style.display = 'flex';
            }
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

$("#btn1").on('click',function () {
    let code = $(this).attr('data-code');
    window.location.href = 'http://www.baidu.com?dqrcode='+code;
});
$("#btn2").on('click',function () {
    let code = $(this).attr('data-code');
    window.location.href = 'http://www.baidu.com?dqrcode='+code;
});
$("#btn3").on('click',function () {
    let code = $(this).attr('data-code');
    window.location.href = 'http://www.baidu.com?dqrcode='+code;
});
$("#btn4").on('click',function () {
    let code = $(this).attr('data-code');
    window.location.href = 'http://www.baidu.com?dqrcode='+code;
});