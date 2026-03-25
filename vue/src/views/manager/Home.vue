<script>
export default {
  name: "Home"
}
</script>

<template>
  <div class="home-wrapper">
    <div class="welcome-card">
      <div class="header-section">
        <i class="el-icon-monitor"></i>
        <h1 class="title"></h1>
      </div>

      <div class="content-section">
        <h2 class="welcome-msg">欢迎您进入主页</h2>
        <div class="divider"></div>
        <div class="clock-box">
          <p class="date-text">今天是：<span class="highlight">{{ currentDate }}</span></p>
          <p class="time-text">现在时间是：<span class="highlight-time">{{ currentTime }}</span></p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data() {
    return {
      currentDate: '',
      currentTime: '',
      timer: null
    }
  },
  created() {
    this.updateTime();
  },
  mounted() {
    this.timer = setInterval(() => {
      this.updateTime();
    }, 1000);
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  },
  methods: {
    updateTime() {
      const now = new Date();
      this.currentDate = `${now.getFullYear()}年${now.getMonth() + 1}月${now.getDate()}日`;
      const hh = String(now.getHours()).padStart(2, '0');
      const mm = String(now.getMinutes()).padStart(2, '0');
      const ss = String(now.getSeconds()).padStart(2, '0');
      this.currentTime = `${hh}:${mm}:${ss}`;
    }
  }
}
</script>

<style scoped>
/* 核心适配逻辑 */
.home-wrapper {
  width: 100%;
  height: calc(100vh - 120px); /* 自动减去头部和面包屑的高度，防止出现滚动条 */
  padding: 20px;
  box-sizing: border-box;
  background-color: #f5f7fa;
  display: flex;
  justify-content: center;
  align-items: center;
}

.welcome-card {
  width: 100%;
  max-width: 800px; /* 限制最大宽度，防止在大屏上太散 */
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.header-section {
  background-color: #409EFF;
  color: white;
  padding: 15px 20px;
  display: flex;
  align-items: center;
}

.header-section i {
  font-size: 24px;
  margin-right: 10px;
}

.title {
  font-size: 18px;
  margin: 0;
  font-weight: 500;
}

.content-section {
  padding: 40px;
  text-align: center;
}

.welcome-msg {
  color: #303133;
  font-size: 26px;
  margin-bottom: 20px;
}

.divider {
  height: 1px;
  background: #ebeef5;
  width: 60%;
  margin: 0 auto 30px;
}

.clock-box {
  background: #fdfdfd;
  border: 1px solid #f0f0f0;
  display: inline-block;
  padding: 20px 40px;
  border-radius: 4px;
}

.date-text, .time-text {
  font-size: 18px;
  color: #606266;
  margin: 10px 0;
}

.highlight {
  color: #409EFF;
  font-weight: bold;
}

.highlight-time {
  color: #67C23A; /* 成功绿，代表系统运行正常 */
  font-family: "Courier New", Courier, monospace;
  font-weight: bold;
  font-size: 24px;
}
</style>