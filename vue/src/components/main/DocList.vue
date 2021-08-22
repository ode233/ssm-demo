<template>
  <el-container
    v-loading.fullscreen.lock="loading">
    <el-table
      :data="tableData.filter(data => !search || data.docName.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column
        label="文件名"
        prop="docName">
      </el-table-column>
      <el-table-column
        label="文件描述"
        prop="docDescribe">
      </el-table-column>
      <el-table-column
        label="编辑人"
        prop="userName">
      </el-table-column>
      <el-table-column
        label="编辑日期"
        prop="date">
      </el-table-column>
      <el-table-column>
        <template slot="header" slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="addVisible = true">新增
          </el-button>
          <el-input
            style="width: 70%"
            v-model="search"
            size="mini"
            placeholder="输入文件名搜索"/>
        </template>
        <template slot-scope="scope">
          <el-link target="_blank" :href="`http://localhost:8081/api/doc/file/${scope.row.docPath}/`" :underline="false" style="margin-right: 10px">
            <el-button size="mini">下载</el-button>
          </el-link>
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      v-loading="loading"
      title="新增"
      :visible.sync="addVisible"
      :close-on-click-modal = "false"
      width="30%"
      center>
      <el-form ref="addForm" :rules="rules" :model="addForm" label-width="80px">
        <el-form-item label="文档名称" prop="docName">
          <el-input v-model="addForm.docName"></el-input>
        </el-form-item>
        <el-form-item label="文档描述">
          <el-input v-model="addForm.docDescribe"></el-input>
        </el-form-item>
        <el-form-item style="margin-bottom: 0">
          <el-upload
            ref="addUpload"
            class="upload-demo"
            :auto-upload="false"
            :multiple="false"
            :limit="1"
            :before-upload="beforeUpload"
            :on-success="uploadSuccess"
            :data="addForm"
            drag
            action="http://localhost:8081/api/doc/">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="addVisible = false">取 消</el-button>
      <el-button type="primary" @click="handleAdd">确 定</el-button>
      </span>
    </el-dialog>

  </el-container>
</template>

<script>

  import service from '../../utils/request'

  export default {
    name: 'DocList',
    created () {
      this.getData()
    },
    data () {
      return {
        loading: false,
        tableData: [],
        search: '',
        addVisible: false,
        addForm:{
          docName:"",
          docDescribe:"",
          docPath:"",
          userName:"",
        },
        rules: {
          docName: [
            { required: true, message: '请填写文件名', trigger: 'blur' }
          ],
        }
      }
    },
    methods: {
      getData () {
        this.loading = true
        service.get('/api/doc/').then((response) => {
          this.tableData = response
          this.loading = false
        })
      },
      handleAdd(){
        if (this.$refs.addUpload.uploadFiles.length === 0){
          this.$message({
            message: '请添加上传文件',
            type: 'warning'
          });
          return
        }
        this.$refs.addForm.validate((valid) => {
          if (valid) {
            this.loading = true
            this.$refs.addUpload.submit()
            this.addVisible = false
          } else {
            return false;
          }
        })
      },
      handleEdit (index, row) {
      },
      handleDelete (index, row) {
        this.loading = true
        service.delete(`/api/doc/${row.docID}/`).then((response) => {
          this.getData()
        })
      },
      beforeUpload(file){
        this.addForm.docPath = file.name
        this.addForm.userName = this.$cookies.get("name")
      },
      uploadSuccess(){
        this.getData()
        this.$refs.addUpload.uploadFiles = []
      }
    },
  }
</script>

<style scoped lang="scss">

  /deep/ .el-dialog--center .el-dialog__body {
    padding-bottom: 0;
  }

</style>
