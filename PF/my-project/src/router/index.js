import Vue from 'vue';
import Router from 'vue-router';
import Index from '@/components/Index';
import Download from '@/components/page/excel/Download';
import Upload from '@/components/page/excel/Upload';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            component: Index,
            name: 'Index',
            children: [
                {
                    path: '/download',
                    name:'Download',
                    component: Download
                },
                {
                    path: '/upload',
                    name:'Upload',
                    component: Upload
                }
            ],
            redirect: "/download"
        },
    ]
})
