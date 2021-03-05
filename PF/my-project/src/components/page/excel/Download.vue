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
                    <el-cascader :options="options" :props="props" v-model="formDate.workplace" collapse-tags clearable placeholder="请选择工作地点" style="width:250px" ></el-cascader>
                </el-form-item>
                <el-form-item label="备注：" prop="remarks">
                    <el-input v-model="formDate.remarks" placeholder="请输入备注" clearable style="width:250px"></el-input>
                </el-form-item>
                <el-row>
                    <el-form-item>
                        <el-button type="primary" @click="onQuery">查询</el-button>
                        <el-button @click="resetForm('formDate')">重置</el-button>
                    </el-form-item>
                    <el-button type="primary" @click="onDownload">下载<i class="el-icon-download el-icon--right"></i></el-button>
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
                    educations:[],
                    grassrootsWorkYearss:[],
                    grassrootsWorkUndergos:[],
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
                props: { multiple: true },
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
                }, {
                    value: 17,
                    label: '西北',
                    children: [{
                        value: 18,
                        label: '陕西',
                        children: [
                            { value: 19, label: '西安' },
                            { value: 20, label: '延安' }
                        ]
                    }, {
                        value: 21,
                        label: '新疆维吾尔族自治区',
                        children: [
                            { value: 22, label: '乌鲁木齐' },
                            { value: 23, label: '克拉玛依' }
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
                var workplace = Array.from(_this.formDate.workplace);
                workplace.forEach(item => {
                    console.log(item.join(""));
                })
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
            //重置
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.formDate.majors = []
                this.formDate.educations = []
                this.formDate.grassrootsWorkYearss = []
                this.formDate.grassrootsWorkUndergos = []
                this.onSubmit()
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
                    link.download = '公务员职位表' // 自定义文件名
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