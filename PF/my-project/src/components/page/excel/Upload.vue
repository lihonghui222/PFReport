<template>
    <div style="width:360px;position:absolute;left:0;right:0;margin: auto;">
        <el-upload drag ref="upload" :limit="1" :auto-upload="false" accept=".xls,.xlsx" action="/api/excel/upload"
                   :on-success="handleFilUploadSuccess" :on-exceed="handleFileNumberLimit" :on-error="handleFilUploadError">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">注意只能上传xls和xlsx文件。上传成功后，会删除以前数据</div>
        </el-upload>
        <br/>
        <el-button size="small" type="primary" @click="submitUpload">立即上传</el-button>
    </div>
</template>

<script>
    export default {
        name: "Upload",
        methods: {
            // 处理文件上传的函数
            submitUpload() {
                // console.log(res,file)
                this.$refs.upload.submit();
            },
            // 文件上传成功时的函数
            handleFilUploadSuccess (res,file) {
                //console.log(res,file,fileList);
                if(file.response.returnCode == 0){
                    this.$message.success("上传成功！");
                }else{
                    this.$message.error("上传失败！请检查文件格式是否正确");
                    this.$refs.upload.clearFiles();
                }
            },
            handleFilUploadError(){
                this.$message.error("上传失败！请联系18101675396查错");
            },
            handleFileNumberLimit (){
                this.$message.success("文件只能同时上传一个！")
            }
        }
    }
</script>

<style scoped>

</style>