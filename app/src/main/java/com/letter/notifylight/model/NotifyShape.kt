package com.letter.notifylight.model

/**
 * 通知图形
 * @property name String 图形名字
 * @property components List<NotifyComponent> 组件列表
 * @constructor 构造器
 *
 * @author Letter(nevermindzzt@gmail.com)
 * @since 1.0.0
 */
data class NotifyShape(var name: String,
                       var components: List<NotifyComponent>) {

    /**
     * 通知图形组件
     * @property type String 组件类型
     * @property x Int x坐标
     * @property y Int y坐标
     * @property startX Int 起始点x坐标
     * @property startY Int 起始点y坐标
     * @property endX Int 结束点x坐标
     * @property endY Int 结束点y坐标
     * @property centerX Int 中心点x坐标
     * @property centerY Int 中心点y坐标
     * @property radius Int 半径
     * @property startAngle Int 开始角度
     * @property endAngle Int 结束角度
     * @property color Int 颜色
     * @property stokeWidth Int 宽度
     * @constructor 构造器
     *
     * @author Letter(nevermindzzt@gmail.com)
     * @since 1.0.0
     */
    data class NotifyComponent constructor(var type: String,
                                           var x: Int,
                                           var y: Int,
                                           var startX: Int,
                                           var startY: Int,
                                           var endX: Int,
                                           var endY: Int,
                                           var centerX: Int,
                                           var centerY: Int,
                                           var radius: Int,
                                           var startAngle: Int,
                                           var endAngle: Int,
                                           var color: Int,
                                           var stokeWidth: Int)
}