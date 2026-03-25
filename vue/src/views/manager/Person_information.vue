<script>
export default {
  name: "Person_information"
}
</script>



<template>
  <div class="profile-container">
    <el-card class="profile-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span class="card-title"><i class="el-icon-s-data"></i> 个人数据中心</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="loadData">刷新数据</el-button>
      </div>

      <el-table :data="tableData" border stripe style="width: 100%" :header-cell-style="{background:'#f5f7fa',color:'#606266'}">
        <el-table-column prop="label" label="维度" width="180"></el-table-column>
        <el-table-column prop="value" label="核心数据内容">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.prop === 'role'" type="info">{{ scope.row.value }}</el-tag>
            <span v-else>{{ scope.row.value }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="120">
          <template slot-scope="scope">
            <el-button v-if="scope.row.editable" size="mini" type="primary" @click="handleEdit(scope.row)">修改</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="time-footer">
        数据同步时间：{{ currentTime }}
      </div>
    </el-card>

    <el-dialog title="更新数据" :visible.sync="dialogVisible" width="400px">
      <el-form :model="form" label-width="80px">
        <el-form-item :label="currentRow.label">
          <el-input v-model="form.newValue" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import request from "@/utils/request"; // 假设你已有封装好的 axios

export default {
  name: 'ProfileTable',
  data() {
    return {
      currentTime: '',
      dialogVisible: false,
      currentRow: {},
      form: { newValue: '' },
      // 严格对应表格维度，不含 password
      tableData: [
        { label: '用户账号', value: '', prop: 'username', editable: false },
        { label: '所属角色', value: '', prop: 'role', editable: false },
        { label: '联系电话', value: '', prop: 'phone', editable: true },
        { label: '电子邮箱', value: '', prop: 'email', editable: true }
      ]
    }
  },
  created() {
    this.loadData();
    this.updateTime();
  },
  methods: {
    // 后续调用接口的预留方法

    updateTime() {
      const now = new Date();
      this.currentTime = now.toLocaleString();
    },
    handleEdit(row) {
      this.currentRow = row;
      this.form.newValue = row.value;
      this.dialogVisible = true;
    },
    async submitUpdate() {
      // 此处可调用修改接口
      this.$message.success(`${this.currentRow.label} 已更新为: ${this.form.newValue}`);
      this.currentRow.value = this.form.newValue;
      this.dialogVisible = false;
    }
  }
}
</script>

<style scoped>
.profile-container {
  padding: 20px;
  background-color: #f0f2f5;
  height: calc(100vh - 110px); /* 适配子路由高度 */
  display: flex;
  justify-content: center;
}

.profile-card {
  width: 100%;
  max-width: 1000px;
  height: fit-content;
}

.card-title {
  font-weight: bold;
  font-size: 16px;
  color: #303133;
}

.time-footer {
  margin-top: 20px;
  font-size: 13px;
  color: #909399;
  text-align: right;
}
</style>