<template>
    <div>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
            <el-submenu index="1">
                <template slot="title">我的工作台</template>
                <router-link :to="{name:'Download'}">
                    <el-menu-item index="1-1">EXCEL导出</el-menu-item>
                </router-link>
                <router-link :to="{name:'Upload'}">
                    <el-menu-item index="1-2">EXCEL导入</el-menu-item>
                </router-link>
            </el-submenu>
        </el-menu>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeIndex: '1-1'
            };
        }
    }
</script>

<style scoped>

</style>
