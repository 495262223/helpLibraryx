package com.treehole.helplibrary.core.bus

/***************************************************************************
 *
 *  https://github.com/Blankj/AndroidUtilCode/tree/master/plugin/bus-gradle-plugin
 *
 *  比 EventBus 更高效的事件总线（BusUtils）
 *
 *  配置
 *
 *  在项目根目录的 build.gradle 中添加 bus 插件：
 *  classpath 'com.blankj:bus-gradle-plugin:latest.release'
 *
 *  然后在 application 模块中使用该插件：
 *  apply plugin: "com.blankj.bus"
 *
 *  配置完后, BaseActivity 会自动注册
 *
 *  建议:
 *      创建模块的 BusTag 管理类 , 继承 BaseBusTagManage 父类 , 该父类不做任何操作 , 用来管理项目中使用的 BusTag
 *
 ****************************************************************************/
object BusUtilsConfig {

    /**
     * 配置完成后请在 Application 中设置 BusUtils 的状态
     */
    var isOpenBusUtils = false

}