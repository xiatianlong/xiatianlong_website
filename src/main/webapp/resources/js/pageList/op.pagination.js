/**
 * 分页配置
 */
  var pageIndex = 1; //页面索引初始值
  var pageSize = 20; //每页显示条数初始化，修改显示条数，修改这里即可
  
  // 分页控件回调事件
  function PageCallback(index, jq) {
      getPageList(index+1, pageSize);
  }
