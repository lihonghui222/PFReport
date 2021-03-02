<template>
    <div>
        <template>
            <el-form :inline="true" label-position="right" label-width="200px" :model="formDate" ref="formDate" class="demo-form-inline">
                <el-form-item label="专业：" prop="major">
                    <!--<el-input v-model="formDate.major" placeholder="请输入专业" clearable></el-input>-->
                    <el-select v-model="formDate.majors" multiple collapse-tags filterable allow-create placeholder="请选择">
                        <el-option v-for="item in majors" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="学历：" prop="education">
                    <el-input v-model="formDate.education" placeholder="请输入学历" clearable></el-input>
                </el-form-item>
                <el-form-item label="基层工作最低年限：" prop="grassrootsWorkYears">
                    <el-input v-model="formDate.grassrootsWorkYears" placeholder="请输入基层工作最低年限" clearable></el-input>
                </el-form-item>
                <el-form-item label="服务基层项目工作经历：" prop="grassrootsWorkUndergo">
                    <el-input v-model="formDate.grassrootsWorkUndergo" placeholder="请输入服务基层项目工作经历" clearable></el-input>
                </el-form-item>
                <el-form-item label="工作地点：" prop="workplace">
                    <el-input v-model="formDate.workplace" placeholder="请输入工作地点" clearable></el-input>
                </el-form-item>
                <el-form-item label="备注：" prop="remarks">
                    <el-input v-model="formDate.remarks" placeholder="请输入备注" clearable></el-input>
                </el-form-item>
                <el-row>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">查询</el-button>
                        <el-button @click="resetForm('formDate')">重置</el-button>
                    </el-form-item>
                    <el-button type="primary">下载<i class="el-icon-download el-icon--right" @click="onDownload"></i></el-button>
                </el-row>
            </el-form>

            <!--<el-table :data="tableData.slice((currentPage-1)*PageSize,currentPage*PageSize)" height="540" border style="width: 100%">-->
            <el-table :data="tableData" height="540" border style="width: 100%">
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="部门代码"><span>{{ props.row.deptCode }}</span></el-form-item>
                            <el-form-item label="部门名称"><span>{{ props.row.deptName }}</span></el-form-item>
                            <el-form-item label="用人司局"><span>{{ props.row.employer }}</span></el-form-item>
                            <el-form-item label="机构性质"><span>{{ props.row.organNature }}</span></el-form-item>
                            <el-form-item label="招考职位"><span>{{ props.row.positionRecruitment }}</span></el-form-item>
                            <el-form-item label="职位属性"><span>{{ props.row.positionAttribute }}</span></el-form-item>
                            <el-form-item label="职位分布"><span>{{ props.row.positionDistribution }}</span></el-form-item>
                            <el-form-item label="职位简介"><span>{{ props.row.positionSynopsis }}</span></el-form-item>
                            <el-form-item label="职位代码"><span>{{ props.row.positionCode }}</span></el-form-item>
                            <el-form-item label="机构层级"><span>{{ props.row.organLevel }}</span></el-form-item>
                            <el-form-item label="考试类别"><span>{{ props.row.examType }}</span></el-form-item>
                            <el-form-item label="招考人数"><span>{{ props.row.recruitmentNumber }}</span></el-form-item>
                            <el-form-item label="专业"><span>{{ props.row.major }}</span></el-form-item>
                            <el-form-item label="学历"><span>{{ props.row.education }}</span></el-form-item>
                            <el-form-item label="学位"><span>{{ props.row.degree }}</span></el-form-item>
                            <el-form-item label="政治面貌"><span>{{ props.row.politicalOutlook }}</span></el-form-item>
                            <el-form-item label="基层工作最低年限"><span>{{ props.row.grassrootsWorkYears }}</span></el-form-item>
                            <el-form-item label="服务基层项目工作经历"><span>{{ props.row.grassrootsWorkUndergo }}</span></el-form-item>
                            <el-form-item label="是否在面试阶段组织专业能力测试"><span>{{ props.row.ifInterviewStage }}</span></el-form-item>
                            <el-form-item label="面试人员比例"><span>{{ props.row.interviewPersonnelRatio }}</span></el-form-item>
                            <el-form-item label="工作地点"><span>{{ props.row.workplace }}</span></el-form-item>
                            <el-form-item label="落户地点"><span>{{ props.row.location }}</span></el-form-item>
                            <el-form-item label="备注"><span>{{ props.row.remarks }}</span></el-form-item>
                            <el-form-item label="部门网站"><span>{{ props.row.deptWebsite }}</span></el-form-item>
                            <el-form-item label="咨询电话1"><span>{{ props.row.consultTel1 }}</span></el-form-item>
                            <el-form-item label="咨询电话2"><span>{{ props.row.consultTel2 }}</span></el-form-item>
                            <el-form-item label="咨询电话3"><span>{{ props.row.consultTel3 }}</span></el-form-item>
                        </el-form>
                    </template>
                </el-table-column>

                <el-table-column type="index"></el-table-column>
                <el-table-column prop="workplace" label="工作地点" width="180"></el-table-column>
                <el-table-column prop="recruitmentNumber" label="招考人数" width="100"></el-table-column>
                <el-table-column prop="positionSynopsis" label="职位简介" width="400"></el-table-column>
                <el-table-column prop="deptWebsite" label="部门网站" width="180"></el-table-column>
                <el-table-column prop="major" label="专业"></el-table-column>
            </el-table>
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
                    :page-sizes="pageSizes" :page-size="PageSize" layout="total, sizes, prev, pager, next, jumper"
                    :total="tableCount">
            </el-pagination>
        </template>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [],//所有数据
                tableCount:0,//数据总数
                currentPage:1,//默认显示第一页
                pageSizes:[10,20,30,40],//个数修改器
                PageSize:10,//默认每页显示10条
                formDate: {
                    majors: [],
                    education:'',
                    grassrootsWorkYears:'',
                    grassrootsWorkUndergo:'',
                    workplace:'',
                    remarks:'',
                    numberIndex: 0,//分页查询起始下标
                    nowPageSize:10//分页查询每页数据条数
                },
                majors: [{
                    value: '哲学',
                    label: '哲学'
                }, {
                    value: '中国语言文学',
                    label: '中国语言文学'
                }, {
                    value: '外国语言文学',
                    label: '外国语言文学'
                }, {
                    value: '新闻传播学',
                    label: '新闻传播学'
                }, {
                    value: '马克思主义理论',
                    label: '马克思主义理论'
                }, {
                    value: '历史学',
                    label: '历史学'
                }, {
                    value: '政治学',
                    label: '政治学'
                }, {
                    value: '社会学',
                    label: '社会学'
                }, {
                    value: '公共管理',
                    label: '公共管理'
                }, {
                    value: '经济学',
                    label: '经济学'
                }, {
                    value: '理论经济学',
                    label: '理论经济学'
                }, {
                    value: '应用经济学',
                    label: '应用经济学'
                }, {
                    value: '财政学',
                    label: '财政学'
                }, {
                    value: '金融学',
                    label: '金融学'
                }, {
                    value: '经济与贸易',
                    label: '经济与贸易'
                }, {
                    value: '工商管理',
                    label: '工商管理'
                }, {
                    value: '统计学',
                    label: '统计学'
                }, {
                    value: '法学',
                    label: '法学'
                }, {
                    value: '心理学',
                    label: '心理学'
                }, {
                    value: '公安学',
                    label: '公安学'
                }, {
                    value: '公安技术',
                    label: '公安技术'
                }, {
                    value: '土木工程',
                    label: '土木工程'
                }, {
                    value: '工程管理',
                    label: '工程管理'
                }, {
                    value: '人力资源管理',
                    label: '人力资源管理'
                }, {
                    value: '公共事业管理',
                    label: '公共事业管理'
                }, {
                    value: '行政管理',
                    label: '行政管理'
                }, {
                    value: '汉语言文学',
                    label: '汉语言文学'
                }, {
                    value: '汉语言',
                    label: '汉语言'
                }, {
                    value: '新闻学',
                    label: '新闻学'
                }, {
                    value: '传播学',
                    label: '传播学'
                }, {
                    value: '会计学',
                    label: '会计学'
                }, {
                    value: '财务管理',
                    label: '财务管理'
                }, {
                    value: '建筑电气与智能化',
                    label: '建筑电气与智能化'
                }, {
                    value: '电力系统及其自动化',
                    label: '电力系统及其自动化'
                }, {
                    value: '结构工程',
                    label: '结构工程'
                }, {
                    value: '工程造价',
                    label: '工程造价'
                }, {
                    value: '城市地下空间工程',
                    label: '城市地下空间工程'
                }, {
                    value: '防灾减灾工程及防护工程',
                    label: '防灾减灾工程及防护工程'
                }]
            }
        },
        methods: {
            handleSizeChange(val) {
                this.PageSize=val// 改变每页显示的条数
                this.currentPage=1// 注意：在改变每页显示的条数时，要将页码显示到第一页
                this.formDate.numberIndex = 0
                this.formDate.nowPageSize = val
                this.onSubmit()
            },
            handleCurrentChange(val) {
                this.currentPage=val// 改变默认的页数
                this.formDate.numberIndex = this.formDate.nowPageSize * (val-1)
                this.onSubmit()
            },
            onSubmit() {
                var _this = this;
                _this.$axios({
                    method:'post',
                    url:'/api/excel/query',
                    data:{
                        pf:_this.formDate
                    }
                }).then((response) =>{          //这里使用了ES6的语法
                    //console.log(response)       //请求成功返回的数据
                    if(response.data != null){
                        _this.tableData = response.data.list;
                        _this.tableCount = response.data.listNumber;
                    }
                }).catch((error) =>{
                    console.log(error);       //请求失败返回的数据
                })
                //console.log(_this.formDate);
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            onDownload(){
                this.$axios({
                    method:'post',
                    url:'/api/excel/download',
                    data:{
                        pf:this.formDate
                    },
                    responseType:'blob'
                }).then((response) =>{          //这里使用了ES6的语法
                    //console.log(response)       //请求成功返回的数据
                    // 利用a标签自定义下载文件名
                    const link = document.createElement('a')
                    // 创建Blob对象，设置文件类型
                    let blob = new Blob([response.data], {type: "application/vnd.ms-excel"})
                    let objectUrl = URL.createObjectURL(blob) // 创建URL
                    link.href = objectUrl
                    link.download = 'xxx' // 自定义文件名
                    link.click() // 下载文件
                    URL.revokeObjectURL(objectUrl); // 释放内存
                }).catch((error) =>{
                    console.log(error);       //请求失败返回的数据
                })
            }
        },
        created(){
            this.onSubmit();
        }
    }
</script>

<style>
    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 270px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 100%;
    }

    .demo-form-inline .el-form-item{
        margin-bottom: 10px;
    }

    /*.demo-table-expand .el-form-item span{
        color:red;
    }*/
</style>