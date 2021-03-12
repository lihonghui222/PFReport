<template>
    <div>
        <template>
            <el-form :inline="true" label-position="right" label-width="200px" :model="formDate" ref="formDate" class="demo-form-inline">
                <el-form-item label="专业：" prop="major">
                    <!--<el-input v-model="formDate.major" placeholder="请输入专业" clearable></el-input>-->
                    <el-select v-model="formDate.majors" multiple collapse-tags filterable allow-create clearable placeholder="请选择专业" style="width:250px">
                        <el-option v-for="item in majors" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="学历：" prop="education">
                    <!--<el-input v-model="formDate.education" placeholder="请输入学历" clearable></el-input>-->
                    <el-select v-model="formDate.educations" multiple collapse-tags filterable allow-create clearable placeholder="请选择学历" style="width:250px">
                        <el-option v-for="item in educations" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="基层工作最低年限：" prop="grassrootsWorkYears">
                    <!--<el-input v-model="formDate.grassrootsWorkYears" placeholder="请输入基层工作最低年限" clearable></el-input>-->
                    <el-select v-model="formDate.grassrootsWorkYearss" multiple collapse-tags filterable allow-create clearable placeholder="请选择基层工作最低年限" style="width:250px">
                        <el-option v-for="item in grassrootsWorkYearss" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="服务基层项目工作经历：" prop="grassrootsWorkUndergo">
                    <!--<el-input v-model="formDate.grassrootsWorkUndergo" placeholder="请输入服务基层项目工作经历" clearable></el-input>-->
                    <el-select v-model="formDate.grassrootsWorkUndergos" multiple collapse-tags filterable allow-create clearable placeholder="请选择服务基层项目工作经历" style="width:250px">
                        <el-option v-for="item in grassrootsWorkUndergos" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="工作地点：" prop="workplace">
                    <!--<el-input v-model="formDate.workplace" placeholder="请输入工作地点" clearable style="width:250px"></el-input>-->
                    <el-cascader :options="options" :props="props" ref="workRef" collapse-tags clearable placeholder="请选择工作地点" style="width:250px"></el-cascader>
                </el-form-item>
                <el-form-item label="备注：" prop="remarks">
                    <!--<el-input v-model="formDate.remarks" placeholder="请输入备注" clearable style="width:250px"></el-input>-->
                    <el-tooltip class="item" effect="dark" content="此处填入数据为备注中不需要存在的信息" placement="top">
                        <el-select v-model="formDate.remarkss" multiple collapse-tags filterable allow-create clearable placeholder="请选择备注" style="width:250px">
                            <el-option v-for="item in remarkss" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-tooltip>
                </el-form-item>
                <el-row>
                    <el-form-item>
                        <el-button type="primary" @click="onQuery">查询</el-button>
                        <el-button @click="resetForm('formDate')">重置</el-button>
                    </el-form-item>
                    <template>
                        <el-popconfirm confirm-button-text='下载' cancel-button-text='不用了' icon="el-icon-info" icon-color="red"
                                title="确定下载吗？下载可能需要一些时间" @onConfirm="onDownload">
                            <el-button slot="reference" type="primary">下载<i class="el-icon-download el-icon--right"></i></el-button>
                        </el-popconfirm>
                    </template>
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
                <el-table-column prop="major" label="专业" ></el-table-column>
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
                    educations:[],
                    grassrootsWorkYearss:[],
                    grassrootsWorkUndergos:[],
                    workplaces:[],
                    remarkss:[],
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
                }],
                educations: [{
                    value: '大专',
                    label: '大专'

                },{
                    value: '本科',
                    label: '本科'
                },{
                    value: '硕士研究生',
                    label: '硕士研究生'
                },{
                    value: '博士研究生',
                    label: '博士研究生'
                }],
                grassrootsWorkYearss: [{
                    value: '一年',
                    label: '一年'

                },{
                    value: '二年',
                    label: '二年'
                },{
                    value: '三年',
                    label: '三年'
                },{
                    value: '五年以上',
                    label: '五年以上'
                },{
                    value: '无限制',
                    label: '无限制'
                }],
                grassrootsWorkUndergos: [{
                    value: '“三支一扶”计划',
                    label: '“三支一扶”计划'
                },{
                    value: '退役士兵',
                    label: '退役士兵'
                },{
                    value: '大学生志愿服务西部计划',
                    label: '大学生志愿服务西部计划'
                },{
                    value: '大学生村官',
                    label: '大学生村官'
                },{
                    value: '学校教师特设岗位计划',
                    label: '学校教师特设岗位计划'
                },{
                    value: '无限制',
                    label: '无限制'
                }],
                remarkss: [{
                    value: '男',
                    label: '男'
                },{
                    value: '女',
                    label: '女'
                },{
                    value: '毕业生',
                    label: '毕业生'
                },{
                    value: '残疾',
                    label: '残疾'
                },{
                    value: '加班',
                    label: '加班'
                },{
                    value: '出差',
                    label: '出差'
                },{
                    value: '夜班',
                    label: '夜班'
                },{
                    value: '英语四级',
                    label: '英语四级'
                },{
                    value: '英语六级',
                    label: '英语六级'
                },{
                    value: '2年',
                    label: '2年'
                },{
                    value: '户籍人员',
                    label: '户籍人员'
                },{
                    value: '户口',
                    label: '户口'
                }],
                props: { multiple: true},
                options: [{
                    value: '上海市',
                    label: '上海市',
                    children: [{
                        value: '嘉定区',
                        label: '嘉定区'
                    }, {
                        value: '奉贤区',
                        label: '奉贤区',
                    }, {
                        value: '宝山区',
                        label: '宝山区',
                    }, {
                        value: '崇明区',
                        label: '崇明区',
                    }, {
                        value: '徐汇区',
                        label: '徐汇区',
                    }, {
                        value: '普陀区',
                        label: '普陀区',
                    }, {
                        value: '杨浦区',
                        label: '杨浦区',
                    }, {
                        value: '松江区',
                        label: '松江区',
                    }, {
                        value: '浦东新区',
                        label: '浦东新区',
                    }, {
                        value: '虹口区',
                        label: '虹口区',
                    }, {
                        value: '金山区',
                        label: '金山区',
                    }, {
                        value: '长宁区',
                        label: '长宁区',
                    }, {
                        value: '闵行区',
                        label: '闵行区',
                    }, {
                        value: '青浦区',
                        label: '青浦区',
                    }, {
                        value: '静安区',
                        label: '静安区',
                    }, {
                        value: '黄浦区',
                        label: '黄浦区',
                    }]
                },{
                    value: '云南省',
                    label: '云南省',
                    children: [{
                        value: '临沧市',
                        label: '临沧市',
                        children: [
                            { value: '临翔区', label: '临翔区' },
                            { value: '云县', label: '云县' },
                            { value: '凤庆县', label: '凤庆县' },
                            { value: '双江拉祜族佤族布朗族傣族自治县', label: '双江拉祜族佤族布朗族傣族自治县' },
                            { value: '永德县', label: '永德县' },
                            { value: '沧源佤族自治县', label: '沧源佤族自治县' },
                            { value: '耿马傣族佤族自治县', label: '耿马傣族佤族自治县' },
                            { value: '镇康县', label: '镇康县' }
                        ]
                    }, {
                        value: '丽江市',
                        label: '丽江市',
                        children: [
                            { value: '华坪县', label: '华坪县' },
                            { value: '古城区', label: '古城区' },
                            { value: '宁蒗彝族自治县', label: '宁蒗彝族自治县' },
                            { value: '永胜县', label: '永胜县' },
                            { value: '玉龙纳西族自治县', label: '玉龙纳西族自治县' }
                        ]
                    }, {
                        value: '保山市',
                        label: '保山市',
                        children: [
                            { value: '施甸县', label: '施甸县' },
                            { value: '昌宁县', label: '昌宁县' },
                            { value: '腾冲市', label: '腾冲市' },
                            { value: '隆阳区', label: '隆阳区' },
                            { value: '龙陵县', label: '龙陵县' }
                        ]
                    },{
                        value: '大理白族自治州',
                        label: '大理白族自治州',
                        children: [
                            { value: '云龙县', label: '云龙县' },
                            { value: '剑川县', label: '剑川县' },
                            { value: '南涧彝族自治县', label: '南涧彝族自治县' },
                            { value: '宾川县', label: '宾川县' },
                            { value: '巍山彝族回族自治县', label: '巍山彝族回族自治县' },
                            { value: '弥渡县', label: '弥渡县' },
                            { value: '永平县', label: '永平县' },
                            { value: '洱源县', label: '洱源县' },
                            { value: '漾濞彝族自治县', label: '漾濞彝族自治县' },
                            { value: '祥云县', label: '祥云县' },
                            { value: '鹤庆县', label: '鹤庆县' }
                        ]
                    },{
                        value: '德宏傣族景颇族自治州',
                        label: '德宏傣族景颇族自治州',
                        children: [
                            { value: '梁河县', label: '梁河县' },
                            { value: '瑞丽市', label: '瑞丽市' },
                            { value: '盈江县', label: '盈江县' },
                            { value: '芒市', label: '芒市' },
                            { value: '陇川县', label: '陇川县' }
                        ]
                    },{
                        value: '怒江傈僳族自治州',
                        label: '怒江傈僳族自治州',
                        children: [
                            { value: '兰坪白族普米族自治县', label: '兰坪白族普米族自治县' },
                            { value: '泸水市', label: '泸水市' },
                            { value: '福贡县', label: '福贡县' },
                            { value: '贡山独龙族怒族自治县', label: '贡山独龙族怒族自治县' }
                        ]
                    },{
                        value: '文山壮族苗族自治州',
                        label: '文山壮族苗族自治州',
                        children: [
                            { value: '丘北县', label: '丘北县' },
                            { value: '富宁县', label: '富宁县' },
                            { value: '广南县', label: '广南县' },
                            { value: '文山市', label: '文山市' },
                            { value: '砚山县', label: '砚山县' },
                            { value: '西畴县', label: '西畴县' },
                            { value: '马关县', label: '马关县' },
                            { value: '麻栗坡县', label: '麻栗坡县' }
                        ]
                    },{
                        value: '昆明市',
                        label: '昆明市',
                        children: [
                            { value: '东川区', label: '东川区' },
                            { value: '呈贡区', label: '呈贡区' },
                            { value: '安宁市', label: '安宁市' },
                            { value: '官渡区', label: '官渡区' },
                            { value: '宜良县', label: '宜良县' },
                            { value: '富民县', label: '富民县' },
                            { value: '寻甸回族彝族自治县', label: '寻甸回族彝族自治县' },
                            { value: '嵩明县', label: '嵩明县' },
                            { value: '晋宁区', label: '晋宁区' },
                            { value: '石林彝族自治县', label: '石林彝族自治县' },
                            { value: '禄劝彝族苗族自治县', label: '禄劝彝族苗族自治县' },
                            { value: '西山区', label: '西山区' }
                        ]
                    },{
                        value: '昭通市',
                        label: '昭通市',
                        children: [
                            { value: '大关县', label: '大关县' },
                            { value: '巧家县', label: '巧家县' },
                            { value: '彝良县', label: '彝良县' },
                            { value: '昭阳区', label: '昭阳区' },
                            { value: '水富县', label: '水富县' },
                            { value: '永善县', label: '永善县' },
                            { value: '盐津县', label: '盐津县' },
                            { value: '绥江县', label: '绥江县' },
                            { value: '镇雄县', label: '镇雄县' },
                            { value: '鲁甸县', label: '鲁甸县' }
                        ]
                    },{
                        value: '普洱市',
                        label: '普洱市',
                        children: [
                            { value: '墨江哈尼族自治县', label: '墨江哈尼族自治县' },
                            { value: '孟连傣族拉祜族佤族自治县', label: '孟连傣族拉祜族佤族自治县' },
                            { value: '宁洱哈尼族彝族自治县', label: '宁洱哈尼族彝族自治县' },
                            { value: '思茅区', label: '思茅区' },
                            { value: '景东彝族自治县', label: '景东彝族自治县' },
                            { value: '景谷傣族彝族自治县', label: '景谷傣族彝族自治县' },
                            { value: '江城哈尼族彝族自治县', label: '江城哈尼族彝族自治县' },
                            { value: '澜沧拉祜族自治县', label: '澜沧拉祜族自治县' },
                            { value: '西盟佤族自治县', label: '西盟佤族自治县' },
                            { value: '镇沅彝族哈尼族拉祜族自治县', label: '镇沅彝族哈尼族拉祜族自治县' }
                        ]
                    },{
                        value: '曲靖市',
                        label: '曲靖市',
                        children: [
                            { value: '会泽县', label: '会泽县' },
                            { value: '宣威市', label: '宣威市' },
                            { value: '富源县', label: '富源县' },
                            { value: '师宗县', label: '师宗县' },
                            { value: '沾益区', label: '沾益区' },
                            { value: '罗平县', label: '罗平县' },
                            { value: '陆良县', label: '陆良县' },
                            { value: '马龙县', label: '马龙县' },
                            { value: '麒麟区', label: '麒麟区' }
                        ]
                    },{
                        value: '楚雄彝族自治州',
                        label: '楚雄彝族自治州',
                        children: [
                            { value: '元谋县', label: '元谋县' },
                            { value: '南华县', label: '南华县' },
                            { value: '双柏县', label: '双柏县' },
                            { value: '大姚县', label: '大姚县' },
                            { value: '姚安县', label: '姚安县' },
                            { value: '楚雄市', label: '楚雄市' },
                            { value: '武定县', label: '武定县' },
                            { value: '永仁县', label: '永仁县' },
                            { value: '牟定县', label: '牟定县' },
                            { value: '禄丰县', label: '禄丰县' }
                        ]
                    },{
                        value: '玉溪市',
                        label: '玉溪市',
                        children: [
                            { value: '元江哈尼族彝族傣族自治县', label: '元江哈尼族彝族傣族自治县' },
                            { value: '华宁县', label: '华宁县' },
                            { value: '峨山彝族自治县', label: '峨山彝族自治县' },
                            { value: '新平彝族傣族自治县', label: '新平彝族傣族自治县' },
                            { value: '易门县', label: '易门县' },
                            { value: '江川区', label: '江川区' },
                            { value: '澄江县', label: '澄江县' },
                            { value: '红塔区', label: '红塔区' },
                            { value: '通海县', label: '通海县' }
                        ]
                    },{
                        value: '红河哈尼族彝族自治州',
                        label: '红河哈尼族彝族自治州',
                        children: [
                            { value: '个旧市', label: '个旧市' },
                            { value: '元阳县', label: '元阳县' },
                            { value: '屏边苗族自治县', label: '屏边苗族自治县' },
                            { value: '建水县', label: '建水县' },
                            { value: '开远市', label: '开远市' },
                            { value: '弥勒市', label: '弥勒市' },
                            { value: '河口瑶族自治县', label: '河口瑶族自治县' },
                            { value: '泸西县', label: '泸西县' },
                            { value: '石屏县', label: '石屏县' },
                            { value: '红河县', label: '红河县' },
                            { value: '绿春县', label: '绿春县' },
                            { value: '蒙自市', label: '蒙自市' },
                            { value: '金平苗族瑶族傣族自治县', label: '金平苗族瑶族傣族自治县' }
                        ]
                    },{
                        value: '西双版纳傣族自治州',
                        label: '西双版纳傣族自治州',
                        children: [
                            { value: '勐海县', label: '勐海县' },
                            { value: '勐腊县', label: '勐腊县' },
                            { value: '景洪市', label: '景洪市' }
                        ]
                    },{
                        value: '迪庆藏族自治州',
                        label: '迪庆藏族自治州',
                        children: [
                            { value: '德钦县', label: '德钦县' },
                            { value: '维西傈僳族自治县', label: '维西傈僳族自治县' },
                            { value: '香格里拉市', label: '香格里拉市' }
                        ]
                    } ]
                },{
                    value: '内蒙古自治区',
                    label: '内蒙古自治区',
                    children: [{
                        value: '乌兰察布市',
                        label: '乌兰察布市',
                        children: [
                            { value: '丰镇市', label: '丰镇市' },
                            { value: '兴和县', label: '兴和县' },
                            { value: '凉城县', label: '凉城县' },
                            { value: '化德县', label: '化德县' },
                            { value: '卓资县', label: '卓资县' },
                            { value: '商都县', label: '商都县' },
                            { value: '四子王旗', label: '四子王旗' },
                            { value: '察哈尔右翼中旗', label: '察哈尔右翼中旗' },
                            { value: '察哈尔右翼前旗', label: '察哈尔右翼前旗' },
                            { value: '察哈尔右翼后旗', label: '察哈尔右翼后旗' },
                            { value: '集宁区', label: '集宁区' }
                        ]
                    },{
                        value: '乌海市',
                        label: '乌海市',
                        children: [
                            { value: '乌达区', label: '乌达区' },
                            { value: '海勃湾区', label: '海勃湾区' },
                            { value: '海南区', label: '海南区' }
                        ]
                    },{
                        value: '兴安盟',
                        label: '兴安盟',
                        children: [
                            { value: '乌兰浩特市', label: '乌兰浩特市' },
                            { value: '扎赉特旗', label: '扎赉特旗' },
                            { value: '科尔沁右翼中旗', label: '科尔沁右翼中旗' },
                            { value: '科尔沁右翼前旗', label: '科尔沁右翼前旗' },
                            { value: '突泉县', label: '突泉县' },
                            { value: '阿尔山市', label: '阿尔山市' }
                        ]
                    },{
                        value: '包头市',
                        label: '包头市',
                        children: [
                            { value: '东河区', label: '东河区' },
                            { value: '九原区', label: '九原区' },
                            { value: '固阳县', label: '固阳县' },
                            { value: '土默特右旗', label: '土默特右旗' },
                            { value: '昆都仑区', label: '昆都仑区' },
                            { value: '白云鄂博矿区', label: '白云鄂博矿区' },
                            { value: '石拐区', label: '石拐区' },
                            { value: '达尔罕茂明安联合旗', label: '达尔罕茂明安联合旗' },
                            { value: '青山区', label: '青山区' }
                        ]
                    },{
                        value: '呼伦贝尔市',
                        label: '呼伦贝尔市',
                        children: [
                            { value: '扎兰屯市', label: '扎兰屯市' },
                            { value: '扎赉诺尔区', label: '扎赉诺尔区' },
                            { value: '新巴尔虎右旗', label: '新巴尔虎右旗' },
                            { value: '新巴尔虎左旗', label: '新巴尔虎左旗' },
                            { value: '根河市', label: '根河市' },
                            { value: '海拉尔区', label: '海拉尔区' },
                            { value: '满洲里市', label: '满洲里市' },
                            { value: '牙克石市', label: '牙克石市' },
                            { value: '莫力达瓦达斡尔族自治旗', label: '莫力达瓦达斡尔族自治旗' },
                            { value: '鄂伦春自治旗', label: '鄂伦春自治旗' },
                            { value: '阿荣旗', label: '阿荣旗' },
                            { value: '陈巴尔虎旗', label: '陈巴尔虎旗' }
                        ]
                    },{
                        value: '呼和浩特市',
                        label: '呼和浩特市',
                        children: [
                            { value: '和林格尔县', label: '和林格尔县' },
                            { value: '回民区', label: '回民区' },
                            { value: '托克托县', label: '托克托县' },
                            { value: '新城区', label: '新城区' },
                            { value: '武川县', label: '武川县' },
                            { value: '玉泉区', label: '玉泉区' },
                            { value: '赛罕区', label: '赛罕区' }
                        ]
                    },{
                        value: '巴彦淖尔市',
                        label: '巴彦淖尔市',
                        children: [
                            { value: '临河区', label: '临河区' },
                            { value: '乌拉特中旗', label: '乌拉特中旗' },
                            { value: '乌拉特前旗', label: '乌拉特前旗' },
                            { value: '乌拉特后旗', label: '乌拉特后旗' },
                            { value: '五原县', label: '五原县' },
                            { value: '杭锦后旗', label: '杭锦后旗' },
                            { value: '磴口县', label: '磴口县' }
                        ]
                    },{
                        value: '赤峰市',
                        label: '赤峰市',
                        children: [
                            { value: '元宝山区', label: '元宝山区' },
                            { value: '克什克腾旗', label: '克什克腾旗' },
                            { value: '喀喇沁旗', label: '喀喇沁旗' },
                            { value: '宁城县', label: '宁城县' },
                            { value: '巴林右旗', label: '巴林右旗' },
                            { value: '巴林左旗', label: '巴林左旗' },
                            { value: '敖汉旗', label: '敖汉旗' },
                            { value: '松山区', label: '松山区' },
                            { value: '林西县', label: '林西县' },
                            { value: '翁牛特旗', label: '翁牛特旗' },
                            { value: '阿鲁科尔沁旗', label: '阿鲁科尔沁旗' }
                        ]
                    },{
                        value: '通辽市',
                        label: '通辽市',
                        children: [
                            { value: '奈曼旗', label: '奈曼旗' },
                            { value: '库伦旗', label: '库伦旗' },
                            { value: '开鲁县', label: '开鲁县' },
                            { value: '扎鲁特旗', label: '扎鲁特旗' },
                            { value: '科尔沁区', label: '科尔沁区' },
                            { value: '科尔沁左翼中旗', label: '科尔沁左翼中旗' },
                            { value: '科尔沁左翼后旗', label: '科尔沁左翼后旗' },
                            { value: '霍林郭勒市', label: '霍林郭勒市' }
                        ]
                    },{
                        value: '鄂尔多斯市',
                        label: '鄂尔多斯市',
                        children: [
                            { value: '东胜区', label: '东胜区' },
                            { value: '乌审旗', label: '乌审旗' },
                            { value: '准格尔旗', label: '准格尔旗' },
                            { value: '杭锦旗', label: '杭锦旗' },
                            { value: '达拉特旗', label: '达拉特旗' },
                            { value: '鄂托克前旗', label: '鄂托克前旗' },
                            { value: '鄂托克旗', label: '鄂托克旗' }
                        ]
                    },{
                        value: '锡林郭勒盟',
                        label: '锡林郭勒盟',
                        children: [
                            { value: '东乌珠穆沁旗', label: '东乌珠穆沁旗' },
                            { value: '二连浩特市', label: '二连浩特市' },
                            { value: '太仆寺旗', label: '太仆寺旗' },
                            { value: '正蓝旗', label: '正蓝旗' },
                            { value: '正镶白旗', label: '正镶白旗' },
                            { value: '苏尼特右旗', label: '苏尼特右旗' },
                            { value: '苏尼特左旗', label: '苏尼特左旗' },
                            { value: '西乌珠穆沁旗', label: '西乌珠穆沁旗' },
                            { value: '锡林浩特市', label: '锡林浩特市' },
                            { value: '镶黄旗', label: '镶黄旗' }
                        ]
                    },{
                        value: '阿拉善盟',
                        label: '阿拉善盟',
                        children: [
                            { value: '阿拉善右旗', label: '阿拉善右旗' },
                            { value: '阿拉善左旗', label: '阿拉善左旗' },
                            { value: '额济纳旗', label: '额济纳旗' }
                        ]
                    } ]
                },{
                    value: '北京市',
                    label: '北京市',
                    children: [{
                        value: '东城区',
                        label: '东城区'
                    },{
                        value: '大兴区',
                        label: '大兴区'
                    },{
                        value: '密云区',
                        label: '密云区'
                    },{
                        value: '平谷区',
                        label: '平谷区'
                    },{
                        value: '延庆区',
                        label: '延庆区'
                    },{
                        value: '怀柔区',
                        label: '怀柔区'
                    },{
                        value: '房山区',
                        label: '房山区'
                    },{
                        value: '昌平区',
                        label: '昌平区'
                    },{
                        value: '朝阳区',
                        label: '朝阳区'
                    },{
                        value: '海淀区',
                        label: '海淀区'
                    },{
                        value: '石景山区',
                        label: '石景山区'
                    },{
                        value: '西城区',
                        label: '西城区'
                    },{
                        value: '通州区',
                        label: '通州区'
                    },{
                        value: '门头沟区',
                        label: '门头沟区'
                    },{
                        value: '顺义区',
                        label: '顺义区'
                    } ]
                },{
                    value: '吉林省',
                    label: '吉林省',
                    children: [{
                        value: '吉林市',
                        label: '吉林市',
                        children: [
                            { value: '丰满区', label: '丰满区' },
                            { value: '昌邑区', label: '昌邑区' },
                            { value: '桦甸市', label: '桦甸市' },
                            { value: '永吉县', label: '永吉县' },
                            { value: '磐石市', label: '磐石市' },
                            { value: '舒兰市', label: '舒兰市' },
                            { value: '船营区', label: '船营区' },
                            { value: '蛟河市', label: '蛟河市' },
                            { value: '龙潭区', label: '龙潭区' }
                        ]
                    },{
                        value: '四平市',
                        label: '四平市',
                        children: [
                            { value: '伊通满族自治县', label: '伊通满族自治县' },
                            { value: '公主岭市', label: '公主岭市' },
                            { value: '双辽市', label: '双辽市' },
                            { value: '梨树县', label: '梨树县' },
                            { value: '铁东区', label: '铁东区' },
                            { value: '铁西区', label: '铁西区' }
                        ]
                    },{
                        value: '延边朝鲜族自治州',
                        label: '延边朝鲜族自治州',
                        children: [
                            { value: '和龙市', label: '和龙市' },
                            { value: '图们市', label: '图们市' },
                            { value: '安图县', label: '安图县' },
                            { value: '延吉市', label: '延吉市' },
                            { value: '敦化市', label: '敦化市' },
                            { value: '汪清县', label: '汪清县' },
                            { value: '珲春市', label: '珲春市' },
                            { value: '龙井市', label: '龙井市' }
                        ]
                    },{
                        value: '松原市',
                        label: '松原市',
                        children: [
                            { value: '乾安县', label: '乾安县' },
                            { value: '前郭尔罗斯蒙古族自治县', label: '前郭尔罗斯蒙古族自治县' },
                            { value: '宁江区', label: '宁江区' },
                            { value: '扶余市', label: '扶余市' },
                            { value: '长岭县', label: '长岭县' }
                        ]
                    },{
                        value: '白城市',
                        label: '白城市',
                        children: [
                            { value: '大安市', label: '大安市' },
                            { value: '洮北区', label: '洮北区' },
                            { value: '洮南市', label: '洮南市' },
                            { value: '通榆县', label: '通榆县' },
                            { value: '镇赉县', label: '镇赉县' }
                        ]
                    },{
                        value: '白山市',
                        label: '白山市',
                        children: [
                            { value: '临江市', label: '临江市' },
                            { value: '抚松县', label: '抚松县' },
                            { value: '江源区', label: '江源区' },
                            { value: '浑江区', label: '浑江区' },
                            { value: '长白朝鲜族自治县', label: '长白朝鲜族自治县' },
                            { value: '靖宇县', label: '靖宇县' }
                        ]
                    },{
                        value: '辽源市',
                        label: '辽源市',
                        children: [
                            { value: '东丰县', label: '东丰县' },
                            { value: '东辽县', label: '东辽县' },
                            { value: '龙山区', label: '龙山区' }
                        ]
                    },{
                        value: '通化市',
                        label: '通化市',
                        children: [
                            { value: '东昌区', label: '东昌区' },
                            { value: '柳河县', label: '柳河县' },
                            { value: '梅河口市', label: '梅河口市' },
                            { value: '辉南县', label: '辉南县' },
                            { value: '通化县', label: '通化县' },
                            { value: '集安市', label: '集安市' }
                        ]
                    },{
                        value: '长春市',
                        label: '长春市',
                        children: [
                            { value: '九台区', label: '九台区' },
                            { value: '二道区', label: '二道区' },
                            { value: '农安县', label: '农安县' },
                            { value: '双阳区', label: '双阳区' },
                            { value: '德惠市', label: '德惠市' },
                            { value: '榆树市', label: '榆树市' },
                            { value: '绿园区', label: '绿园区' }
                        ]
                    } ]
                },{
                    value: '四川省',
                    label: '四川省',
                    children: [{
                        value: '乐山市',
                        label: '乐山市',
                        children: [
                            { value: '峨边彝族自治县', label: '峨边彝族自治县' },
                            { value: '沐川县', label: '沐川县' },
                            { value: '金口河区', label: '金口河区' },
                            { value: '马边彝族自治县', label: '马边彝族自治县' }
                        ]
                    },{
                        value: '内江市',
                        label: '内江市',
                        children: [
                            { value: '东兴区', label: '东兴区' },
                            { value: '威远县', label: '威远县' },
                            { value: '市中区', label: '市中区' },
                            { value: '资中县', label: '资中县' },
                            { value: '隆昌县', label: '隆昌县' }
                        ]
                    },{
                        value: '凉山彝族自治州',
                        label: '凉山彝族自治州',
                        children: [
                            { value: '会东县', label: '会东县' },
                            { value: '会理县', label: '会理县' },
                            { value: '冕宁县', label: '冕宁县' },
                            { value: '喜德县', label: '喜德县' },
                            { value: '宁南县', label: '宁南县' },
                            { value: '布拖县', label: '布拖县' },
                            { value: '德昌县', label: '德昌县' },
                            { value: '昭觉县', label: '昭觉县' },
                            { value: '普格县', label: '普格县' },
                            { value: '木里藏族自治县', label: '木里藏族自治县' },
                            { value: '甘洛县', label: '甘洛县' },
                            { value: '盐源县', label: '盐源县' },
                            { value: '美姑县', label: '美姑县' },
                            { value: '越西县', label: '越西县' },
                            { value: '金阳县', label: '金阳县' },
                            { value: '雷波县', label: '雷波县' }
                        ]
                    },{
                        value: '南充市',
                        label: '南充市',
                        children: [
                            { value: '仪陇县', label: '仪陇县' },
                            { value: '南部县', label: '南部县' },
                            { value: '嘉陵区', label: '嘉陵区' },
                            { value: '营山县', label: '营山县' },
                            { value: '蓬安县', label: '蓬安县' },
                            { value: '西充县', label: '西充县' },
                            { value: '阆中市', label: '阆中市' },
                            { value: '顺庆区', label: '顺庆区' },
                            { value: '高坪区', label: '高坪区' }
                        ]
                    },{
                        value: '宜宾市',
                        label: '宜宾市',
                        children: [
                            { value: '兴文县', label: '兴文县' },
                            { value: '南溪区', label: '南溪区' },
                            { value: '屏山县', label: '屏山县' },
                            { value: '江安县', label: '江安县' },
                            { value: '珙县', label: '珙县' },
                            { value: '筠连县', label: '筠连县' },
                            { value: '翠屏区', label: '翠屏区' },
                            { value: '长宁县', label: '长宁县' },
                            { value: '高县', label: '高县' }
                        ]
                    },{
                        value: '巴中市',
                        label: '巴中市',
                        children: [
                            { value: '南江县', label: '南江县' },
                            { value: '巴州区', label: '巴州区' },
                            { value: '平昌县', label: '平昌县' },
                            { value: '恩阳区', label: '恩阳区' },
                            { value: '通江县', label: '通江县' }
                        ]
                    },{
                        value: '广元市',
                        label: '广元市',
                        children: [
                            { value: '利州区', label: '利州区' },
                            { value: '剑阁县', label: '剑阁县' },
                            { value: '旺苍县', label: '旺苍县' },
                            { value: '昭化区', label: '昭化区' },
                            { value: '朝天区', label: '朝天区' },
                            { value: '苍溪县', label: '苍溪县' },
                            { value: '青川县', label: '青川县' }
                        ]
                    },{
                        value: '广安市',
                        label: '广安市',
                        children: [
                            { value: '前锋区', label: '前锋区' },
                            { value: '华蓥市', label: '华蓥市' },
                            { value: '岳池县', label: '岳池县' },
                            { value: '广安区', label: '广安区' },
                            { value: '武胜县', label: '武胜县' },
                            { value: '邻水县', label: '邻水县' }
                        ]
                    },{
                        value: '德阳市',
                        label: '德阳市',
                        children: [
                            { value: '中江县', label: '中江县' },
                            { value: '什邡市', label: '什邡市' },
                            { value: '广汉市', label: '广汉市' },
                            { value: '旌阳区', label: '旌阳区' },
                            { value: '绵竹市', label: '绵竹市' },
                            { value: '罗江县', label: '罗江县' }
                        ]
                    },{
                        value: '成都市',
                        label: '成都市',
                        children: [
                            { value: '双流区', label: '双流区' },
                            { value: '大邑县', label: '大邑县' },
                            { value: '崇州市', label: '崇州市' },
                            { value: '彭州市', label: '彭州市' },
                            { value: '成华区', label: '成华区' },
                            { value: '新津县', label: '新津县' },
                            { value: '新都区', label: '新都区' },
                            { value: '武侯区', label: '武侯区' },
                            { value: '温江区', label: '温江区' },
                            { value: '简阳市', label: '简阳市' },
                            { value: '蒲江县', label: '蒲江县' },
                            { value: '邛崃市', label: '邛崃市' },
                            { value: '郫都区', label: '郫都区' },
                            { value: '都江堰市', label: '都江堰市' },
                            { value: '金堂县', label: '金堂县' },
                            { value: '金牛区', label: '金牛区' },
                            { value: '锦江区', label: '锦江区' },
                            { value: '青白江区', label: '青白江区' },
                            { value: '青羊区', label: '青羊区' },
                            { value: '龙泉驿区', label: '龙泉驿区' }
                        ]
                    },{
                        value: '攀枝花市',
                        label: '攀枝花市',
                        children: [
                            { value: '东区', label: '东区' },
                            { value: '仁和区', label: '仁和区' },
                            { value: '盐边县', label: '盐边县' },
                            { value: '米易县', label: '米易县' },
                            { value: '西区', label: '西区' }
                        ]
                    },{
                        value: '泸州市',
                        label: '泸州市',
                        children: [
                            { value: '叙永县', label: '叙永县' },
                            { value: '古蔺县', label: '古蔺县' },
                            { value: '合江县', label: '合江县' },
                            { value: '江阳区', label: '江阳区' },
                            { value: '泸县', label: '泸县' },
                            { value: '纳溪区', label: '纳溪区' },
                            { value: '龙马潭区', label: '龙马潭区' }
                        ]
                    },{
                        value: '甘孜藏族自治州',
                        label: '甘孜藏族自治州',
                        children: [
                            { value: '九龙县', label: '九龙县' },
                            { value: '乡城县', label: '乡城县' },
                            { value: '巴塘县', label: '巴塘县' },
                            { value: '得荣县', label: '得荣县' },
                            { value: '德格县', label: '德格县' },
                            { value: '新龙县', label: '新龙县' },
                            { value: '炉霍县', label: '炉霍县' },
                            { value: '理塘县', label: '理塘县' },
                            { value: '甘孜县', label: '甘孜县' },
                            { value: '白玉县', label: '白玉县' },
                            { value: '石渠县', label: '石渠县' },
                            { value: '稻城县', label: '稻城县' },
                            { value: '色达县', label: '色达县' },
                            { value: '道孚县', label: '道孚县' },
                            { value: '雅江县', label: '雅江县' }
                        ]
                    },{
                        value: '眉山市',
                        label: '眉山市',
                        children: [
                            { value: '东坡区', label: '东坡区' },
                            { value: '丹棱县', label: '丹棱县' },
                            { value: '仁寿县', label: '仁寿县' },
                            { value: '彭山区', label: '彭山区' },
                            { value: '洪雅县', label: '洪雅县' },
                            { value: '青神县', label: '青神县' }
                        ]
                    },{
                        value: '绵阳市',
                        label: '绵阳市',
                        children: [
                            { value: '三台县', label: '三台县' },
                            { value: '北川羌族自治县', label: '北川羌族自治县' },
                            { value: '安州区', label: '安州区' },
                            { value: '平武县', label: '平武县' },
                            { value: '梓潼县', label: '梓潼县' },
                            { value: '江油市', label: '江油市' },
                            { value: '涪城区', label: '涪城区' },
                            { value: '游仙区', label: '游仙区' },
                            { value: '盐亭县', label: '盐亭县' }
                        ]
                    },{
                        value: '自贡市',
                        label: '自贡市',
                        children: [
                            { value: '大安区', label: '大安区' },
                            { value: '富顺县', label: '富顺县' },
                            { value: '沿滩区', label: '沿滩区' },
                            { value: '自流井区', label: '自流井区' },
                            { value: '荣县', label: '荣县' },
                            { value: '贡井区', label: '贡井区' }
                        ]
                    },{
                        value: '资阳市',
                        label: '资阳市',
                        children: [
                            { value: '乐至县', label: '乐至县' },
                            { value: '安岳县', label: '安岳县' },
                            { value: '雁江区', label: '雁江区' }
                        ]
                    },{
                        value: '达州市',
                        label: '达州市',
                        children: [
                            { value: '万源市', label: '万源市' },
                            { value: '大竹县', label: '大竹县' },
                            { value: '宣汉县', label: '宣汉县' },
                            { value: '开江县', label: '开江县' },
                            { value: '渠县', label: '渠县' },
                            { value: '达川区', label: '达川区' },
                            { value: '通川区', label: '通川区' }
                        ]
                    },{
                        value: '遂宁市',
                        label: '遂宁市',
                        children: [
                            { value: '大英县', label: '大英县' },
                            { value: '安居区', label: '安居区' },
                            { value: '射洪县', label: '射洪县' },
                            { value: '船山区', label: '船山区' },
                            { value: '蓬溪县', label: '蓬溪县' }
                        ]
                    },{
                        value: '阿坝藏族羌族自治州',
                        label: '阿坝藏族羌族自治州',
                        children: [
                            { value: '九寨沟县', label: '九寨沟县' },
                            { value: '壤塘县', label: '壤塘县' },
                            { value: '小金县', label: '小金县' },
                            { value: '松潘县', label: '松潘县' },
                            { value: '汶川县', label: '汶川县' },
                            { value: '理县', label: '理县' },
                            { value: '红原县', label: '红原县' },
                            { value: '若尔盖县', label: '若尔盖县' },
                            { value: '茂县', label: '茂县' },
                            { value: '金川县', label: '金川县' },
                            { value: '阿坝县', label: '阿坝县' },
                            { value: '马尔康市', label: '马尔康市' },
                            { value: '黑水县', label: '黑水县' }
                        ]
                    },{
                        value: '雅安市',
                        label: '雅安市',
                        children: [
                            { value: '名山区', label: '名山区' },
                            { value: '天全县', label: '天全县' },
                            { value: '宝兴县', label: '宝兴县' },
                            { value: '汉源县', label: '汉源县' },
                            { value: '石棉县', label: '石棉县' },
                            { value: '芦山县', label: '芦山县' },
                            { value: '荥经县', label: '荥经县' },
                        ]
                    } ]
                },{
                    value: '天津市',
                    label: '天津市',
                    children: [{
                        value: '东丽区',
                        label: '东丽区',
                    },{
                        value: '北辰区',
                        label: '北辰区',
                    },{
                        value: '南开区',
                        label: '南开区',
                    },{
                        value: '和平区',
                        label: '和平区',
                    },{
                        value: '宁河区',
                        label: '宁河区',
                    },{
                        value: '宝坻区',
                        label: '宝坻区',
                    },{
                        value: '武清区',
                        label: '武清区',
                    },{
                        value: '河东区',
                        label: '河东区',
                    },{
                        value: '河北区',
                        label: '河北区',
                    },{
                        value: '河西区',
                        label: '河西区',
                    },{
                        value: '津南区',
                        label: '津南区',
                    },{
                        value: '滨海新区',
                        label: '滨海新区',
                    },{
                        value: '红桥区',
                        label: '红桥区',
                    },{
                        value: '蓟州区',
                        label: '蓟州区',
                    },{
                        value: '西青区',
                        label: '西青区',
                    },{
                        value: '静海区',
                        label: '静海区',
                    }]
                },{
                    value: '宁夏回族自治区',
                    label: '宁夏回族自治区',
                    children: [{
                        value: '中卫市',
                        label: '中卫市',
                        children: [
                            { value: '中宁县', label: '中宁县' },
                            { value: '沙坡头区', label: '沙坡头区' }
                        ]
                    },{
                        value: '吴忠市',
                        label: '吴忠市',
                        children: [
                            { value: '利通区', label: '利通区' },
                            { value: '青铜峡市', label: '青铜峡市' }
                        ]
                    },{
                        value: '固原市',
                        label: '固原市',
                        children: [
                            { value: '原州区', label: '原州区' }
                        ]
                    },{
                        value: '石嘴山市',
                        label: '石嘴山市',
                        children: [
                            { value: '大武口区', label: '大武口区' },
                            { value: '平罗县', label: '平罗县' },
                            { value: '惠农区', label: '惠农区' }
                        ]
                    },{
                        value: '银川市',
                        label: '银川市',
                        children: [
                            { value: '兴庆区', label: '兴庆区' },
                            { value: '永宁县', label: '永宁县' },
                            { value: '灵武市', label: '灵武市' },
                            { value: '西夏区', label: '西夏区' },
                            { value: '贺兰县', label: '贺兰县' },
                            { value: '金凤区', label: '金凤区' }
                        ]
                    } ]
                },{
                    value: '安徽省',
                    label: '安徽省',
                    children: [{
                        value: '亳州市',
                        label: '亳州市',
                        children: [
                            { value: '利辛县', label: '利辛县' },
                            { value: '涡阳县', label: '涡阳县' },
                            { value: '蒙城县', label: '蒙城县' },
                            { value: '谯城区', label: '谯城区' }
                        ]
                    },{
                        value: '六安市',
                        label: '六安市',
                        children: [
                            { value: '叶集区', label: '叶集区' },
                            { value: '舒城县', label: '舒城县' },
                            { value: '裕安区', label: '裕安区' },
                            { value: '金安区', label: '金安区' },
                            { value: '金寨县', label: '金寨县' },
                            { value: '霍山县', label: '霍山县' },
                            { value: '霍邱县', label: '霍邱县' }
                        ]
                    },{
                        value: '合肥市',
                        label: '合肥市',
                        children: [
                            { value: '包河区', label: '包河区' },
                            { value: '巢湖市', label: '巢湖市' },
                            { value: '庐江县', label: '庐江县' },
                            { value: '庐阳区', label: '庐阳区' },
                            { value: '瑶海区', label: '瑶海区' },
                            { value: '肥东县', label: '肥东县' },
                            { value: '肥西县', label: '肥西县' },
                            { value: '蜀山区', label: '蜀山区' },
                            { value: '长丰县', label: '长丰县' }
                        ]
                    },{
                        value: '安庆市',
                        label: '安庆市',
                        children: [
                            { value: '大观区', label: '大观区' },
                            { value: '太湖县', label: '太湖县' },
                            { value: '宜秀区', label: '宜秀区' },
                            { value: '宿松县', label: '宿松县' },
                            { value: '岳西县', label: '岳西县' },
                            { value: '怀宁县', label: '怀宁县' },
                            { value: '望江县', label: '望江县' },
                            { value: '桐城市', label: '桐城市' },
                            { value: '潜山县', label: '潜山县' },
                            { value: '迎江区', label: '迎江区' }
                        ]
                    },{
                        value: '宣城市',
                        label: '宣城市',
                        children: [
                            { value: '宁国市', label: '宁国市' },
                            { value: '宣州区', label: '宣州区' },
                            { value: '旌德县', label: '旌德县' },
                            { value: '泾县', label: '泾县' },
                            { value: '绩溪县', label: '绩溪县' },
                            { value: '郎溪县', label: '郎溪县' }
                        ]
                    },{
                        value: '宿州市',
                        label: '宿州市',
                        children: [
                            { value: '埇桥区', label: '埇桥区' },
                            { value: '泗县', label: '泗县' },
                            { value: '灵璧县', label: '灵璧县' },
                            { value: '砀山县', label: '砀山县' },
                            { value: '萧县', label: '萧县' }
                        ]
                    },{
                        value: '池州市',
                        label: '池州市',
                        children: [
                            { value: '东至县', label: '东至县' },
                            { value: '石台县', label: '石台县' },
                            { value: '贵池区', label: '贵池区' },
                            { value: '青阳县', label: '青阳县' }
                        ]
                    },{
                        value: '蚌埠市',
                        label: '蚌埠市',
                        children: [
                            { value: '五河县', label: '五河县' },
                            { value: '固镇县', label: '固镇县' },
                            { value: '怀远县', label: '怀远县' },
                            { value: '淮上区', label: '淮上区' },
                            { value: '禹会区', label: '禹会区' },
                            { value: '蚌山区', label: '蚌山区' },
                            { value: '龙子湖区', label: '龙子湖区' }
                        ]
                    },{
                        value: '铜陵市',
                        label: '铜陵市',
                        children: [
                            { value: '义安区', label: '义安区' },
                            { value: '枞阳县', label: '枞阳县' },
                            { value: '郊区', label: '郊区' },
                            { value: '铜官区', label: '铜官区' }
                        ]
                    },{
                        value: '阜阳市',
                        label: '阜阳市',
                        children: [
                            { value: '临泉县', label: '临泉县' },
                            { value: '太和县', label: '太和县' },
                            { value: '界首市', label: '界首市' },
                            { value: '阜南县', label: '阜南县' },
                            { value: '颍上县', label: '颍上县' },
                            { value: '颍东区', label: '颍东区' },
                            { value: '颍州区', label: '颍州区' },
                            { value: '颍泉区', label: '颍泉区' }
                        ]
                    },{
                        value: '马鞍山市',
                        label: '马鞍山市',
                        children: [
                            { value: '博望区', label: '博望区' },
                            { value: '含山县', label: '含山县' },
                            { value: '和县', label: '和县' },
                            { value: '当涂县', label: '当涂县' },
                            { value: '花山区', label: '花山区' }
                        ]
                    },{
                        value: '黄山市',
                        label: '黄山市',
                        children: [
                            { value: '休宁县', label: '休宁县' },
                            { value: '屯溪区', label: '屯溪区' },
                            { value: '徽州区', label: '徽州区' },
                            { value: '歙县', label: '歙县' },
                            { value: '祁门县', label: '祁门县' },
                            { value: '黄山区', label: '黄山区' },
                            { value: '黟县', label: '黟县' }
                        ]
                    } ]
                },{
                    value: '内蒙古自治区',
                    label: '内蒙古自治区',
                    children: [{
                        value: '乌兰察布市',
                        label: '乌兰察布市',
                        children: [
                            { value: '丰镇市', label: '丰镇市' },
                            { value: '兴和县', label: '兴和县' },
                            { value: '凉城县', label: '凉城县' },
                            { value: '化德县', label: '化德县' },
                            { value: '卓资县', label: '卓资县' },
                            { value: '商都县', label: '商都县' },
                            { value: '四子王旗', label: '四子王旗' },
                            { value: '察哈尔右翼中旗', label: '察哈尔右翼中旗' },
                            { value: '察哈尔右翼前旗', label: '察哈尔右翼前旗' },
                            { value: '察哈尔右翼后旗', label: '察哈尔右翼后旗' },
                            { value: '集宁区', label: '集宁区' }
                        ]
                    } ]
                }, {
                    value: '黑龙江省',
                    label: '黑龙江省',
                    children: [{
                        value: '七台河市',
                        label: '七台河市',
                        children: [
                            {value: '勃利县', label: '勃利县'},
                            {value: '桃山区', label: '桃山区'}
                        ]
                    }, {
                        value: '伊春市',
                        label: '伊春市',
                        children: [
                            {value: '上甘岭区', label: '上甘岭区'},
                            {value: '五营区', label: '五营区'},
                            {value: '伊春区', label: '伊春区'},
                            {value: '南岔区', label: '南岔区'},
                            {value: '友好区', label: '友好区'},
                            {value: '嘉荫县', label: '嘉荫县'},
                            {value: '新青区', label: '新青区'},
                            {value: '汤旺河区', label: '汤旺河区'},
                            {value: '红星区', label: '红星区'},
                            {value: '美溪区', label: '美溪区'},
                            {value: '翠峦区', label: '翠峦区'},
                            {value: '西林区', label: '西林区'},
                            {value: '金山屯区', label: '金山屯区'},
                            {value: '铁力市', label: '铁力市'}
                        ]
                    }, {
                        value: '佳木斯市',
                        label: '佳木斯市',
                        children: [
                            {value: '东风区', label: '东风区'},
                            {value: '前进区', label: '前进区'},
                            {value: '同江市', label: '同江市'},
                            {value: '向阳区', label: '向阳区'},
                            {value: '富锦市', label: '富锦市'},
                            {value: '抚远市', label: '抚远市'},
                            {value: '桦南县', label: '桦南县'},
                            {value: '桦川县', label: '桦川县'},
                            {value: '汤原县', label: '汤原县'},
                            {value: '郊区', label: '郊区'}
                        ]
                    }, {
                        value: '双鸭山市',
                        label: '双鸭山市',
                        children: [
                            {value: '友谊县', label: '友谊县'},
                            {value: '四方台区', label: '四方台区'},
                            {value: '宝山区', label: '宝山区'},
                            {value: '宝清县', label: '宝清县'},
                            {value: '尖山区', label: '尖山区'},
                            {value: '岭东区', label: '岭东区'},
                            {value: '集贤县', label: '集贤县'},
                            {value: '饶河县', label: '饶河县'}
                        ]
                    }, {
                        value: '哈尔滨市',
                        label: '哈尔滨市',
                        children: [
                            {value: '五常市', label: '五常市'},
                            {value: '依兰县', label: '依兰县'},
                            {value: '南岗区', label: '南岗区'},
                            {value: '双城区', label: '双城区'},
                            {value: '呼兰区', label: '呼兰区'},
                            {value: '宾县', label: '宾县'},
                            {value: '尚志市', label: '尚志市'},
                            {value: '巴彦县', label: '巴彦县'},
                            {value: '平房区', label: '平房区'},
                            {value: '延寿县', label: '延寿县'},
                            {value: '方正县', label: '方正县'},
                            {value: '木兰县', label: '木兰县'},
                            {value: '松北区', label: '松北区'},
                            {value: '通河县', label: '通河县'},
                            {value: '道外区', label: '道外区'},
                            {value: '道里区', label: '道里区'},
                            {value: '阿城区', label: '阿城区'},
                            {value: '香坊区', label: '香坊区'}
                        ]
                    }, {
                        value: '大兴安岭地区',
                        label: '大兴安岭地区',
                        children: [
                            {value: '呼玛县', label: '呼玛县'},
                            {value: '塔河县', label: '塔河县'},
                            {value: '漠河县', label: '漠河县'}
                        ]
                    }, {
                        value: '大庆市',
                        label: '大庆市',
                        children: [
                            {value: '大同区', label: '大同区'},
                            {value: '杜尔伯特蒙古族自治县', label: '杜尔伯特蒙古族自治县'},
                            {value: '林甸县', label: '林甸县'},
                            {value: '红岗区', label: '红岗区'},
                            {value: '肇州县', label: '肇州县'},
                            {value: '肇源县', label: '肇源县'},
                            {value: '萨尔图区', label: '萨尔图区'},
                            {value: '让胡路区', label: '让胡路区'},
                            {value: '龙凤区', label: '龙凤区'}
                        ]
                    }, {
                        value: '牡丹江市',
                        label: '牡丹江市',
                        children: [
                            {value: '东宁市', label: '东宁市'},
                            {value: '东安区', label: '东安区'},
                            {value: '宁安市', label: '宁安市'},
                            {value: '林口县', label: '林口县'},
                            {value: '海林市', label: '海林市'},
                            {value: '爱民区', label: '爱民区'},
                            {value: '穆棱市', label: '穆棱市'},
                            {value: '绥芬河市', label: '绥芬河市'},
                            {value: '西安区', label: '西安区'},
                            {value: '阳明区', label: '阳明区'}
                        ]
                    }, {
                        value: '绥化市',
                        label: '绥化市',
                        children: [
                            {value: '兰西县', label: '兰西县'},
                            {value: '北林区', label: '北林区'},
                            {value: '安达市', label: '安达市'},
                            {value: '庆安县', label: '庆安县'},
                            {value: '明水县', label: '明水县'},
                            {value: '望奎县', label: '望奎县'},
                            {value: '海伦市', label: '海伦市'},
                            {value: '绥棱县', label: '绥棱县'},
                            {value: '肇东市', label: '肇东市'},
                            {value: '青冈县', label: '青冈县'}
                        ]
                    }, {
                        value: '鸡西市',
                        label: '鸡西市',
                        children: [
                            {value: '城子河区', label: '城子河区'},
                            {value: '密山市', label: '密山市'},
                            {value: '恒山区', label: '恒山区'},
                            {value: '梨树区', label: '梨树区'},
                            {value: '滴道区', label: '滴道区'},
                            {value: '虎林市', label: '虎林市'},
                            {value: '鸡东县', label: '鸡东县'},
                            {value: '鸡冠区', label: '鸡冠区'},
                            {value: '麻山区', label: '麻山区'}
                        ]
                    }, {
                        value: '鹤岗市',
                        label: '鹤岗市',
                        children: [
                            {value: '东山区', label: '东山区'},
                            {value: '兴安区', label: '兴安区'},
                            {value: '兴山区', label: '兴山区'},
                            {value: '南山区', label: '南山区'},
                            {value: '向阳区', label: '向阳区'},
                            {value: '工农区', label: '工农区'},
                            {value: '绥滨县', label: '绥滨县'},
                            {value: '萝北县', label: '萝北县'}
                        ]
                    }, {
                        value: '黑河市',
                        label: '黑河市',
                        children: [
                            {value: '五大连池市', label: '五大连池市'},
                            {value: '北安市', label: '北安市'},
                            {value: '嫩江县', label: '嫩江县'},
                            {value: '吴县', label: '吴县'},
                            {value: '爱辉区', label: '爱辉区'},
                            {value: '逊克县', label: '逊克县'}
                        ]
                    }, {
                        value: '齐齐哈尔市',
                        label: '齐齐哈尔市',
                        children: [
                            {value: '依安县', label: '依安县'},
                            {value: '克东县', label: '克东县'},
                            {value: '克山县', label: '克山县'},
                            {value: '富拉尔基区', label: '富拉尔基区'},
                            {value: '富裕县', label: '富裕县'},
                            {value: '昂昂溪区', label: '昂昂溪区'},
                            {value: '梅里斯达斡尔族区', label: '梅里斯达斡尔族区'},
                            {value: '泰来县', label: '泰来县'},
                            {value: '甘南县', label: '甘南县'},
                            {value: '碾子山区', label: '碾子山区'},
                            {value: '讷河市', label: '讷河市'},
                            {value: '铁锋区', label: '铁锋区'},
                            {value: '龙江县', label: '龙江县'},
                            {value: '龙沙区', label: '龙沙区'}
                        ]
                    }]
                }]
            }
        },
        methods: {
            //修改每页显示条数时触发事件
            handleSizeChange(val) {
                this.PageSize=val// 改变每页显示的条数
                this.currentPage=1// 注意：在改变每页显示的条数时，要将页码显示到第一页
                this.formDate.numberIndex = 0
                this.formDate.nowPageSize = val
                this.onSubmit()
            },
            //变更页码时触发事件
            handleCurrentChange(val) {
                this.currentPage=val// 改变默认的页数
                this.formDate.numberIndex = this.formDate.nowPageSize * (val-1)
                this.onSubmit()
            },
            //点击查询时触发事件
            onQuery(){
                this.currentPage=1
                this.formDate.numberIndex = 0
                this.onSubmit()
            },
            //数据查询事件
            onSubmit() {
                var _this = this;
                //工作地点的筛选
                if(_this.$refs.workRef){
                    var workplace = Array.from(_this.$refs.workRef.getCheckedNodes());
                    workplace.forEach(item => {
                        //当父节点存在且父节点未被选中的情况下，将参数拼接
                        if(!(item.parent!=null && item.parent.checked==true )){
                            var paths = Array.from(item.path);
                            _this.formDate.workplaces.push(paths.join(""));
                        }
                    })
                }

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
                _this.formDate.workplaces = []
                //console.log(_this.formDate);
            },
            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.formDate.majors = []
                this.formDate.educations = []
                this.formDate.grassrootsWorkYearss = []
                this.formDate.grassrootsWorkUndergos = []
                this.$refs.workRef.$refs.panel.clearCheckedNodes();
                this.formDate.remarkss = []
                this.onSubmit()
            },
            onDownload(){
                const loading = this.$loading({
                    lock: true,
                    text: '文件下载中',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                //工作地点的筛选
                if(this.$refs.workRef){
                    var workplace = Array.from(this.$refs.workRef.getCheckedNodes());
                    workplace.forEach(item => {
                        //当父节点存在且父节点未被选中的情况下，将参数拼接
                        if(!(item.parent!=null && item.parent.checked==true )){
                            var paths = Array.from(item.path);
                            this.formDate.workplaces.push(paths.join(""));
                        }
                    })
                }
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
                    link.download = '公务员职位表' // 自定义文件名
                    link.click() // 下载文件
                    URL.revokeObjectURL(objectUrl); // 释放内存

                    //关闭加载图标
                    loading.close();
                    this.$message({
                        showClose: true,
                        message: '下载成功！',
                        type: 'success'
                    });
                }).catch((error) =>{
                    console.log(error);       //请求失败返回的数据
                })
                this.formDate.workplaces = []
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