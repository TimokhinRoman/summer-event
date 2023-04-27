(function(){"use strict";var n={5795:function(n,e,t){var r=t(9963),o=t(6252);function a(n,e){const t=(0,o.up)("router-view");return(0,o.wg)(),(0,o.j4)(t)}var i=t(3744);const u={},s=(0,i.Z)(u,[["render",a]]);var l=s,d=t(2201);function c(n,e,t,r,o,a){return" Home View "}var m={name:"HomeView",components:{}};const v=(0,i.Z)(m,[["render",c]]);var f=v;const p=(0,o._)("p",null,"Admin View",-1);function w(n,e,t,r,a,i){const u=(0,o.up)("router-link"),s=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)(o.HY,null,[p,(0,o._)("nav",null,[(0,o.Wm)(u,{to:{name:"Admin"}},{default:(0,o.w5)((()=>[(0,o.Uk)("Домой")])),_:1}),(0,o.Uk)(" | "),(0,o.Wm)(u,{to:{name:"Events"}},{default:(0,o.w5)((()=>[(0,o.Uk)("События")])),_:1})]),(0,o.Wm)(s)],64)}var k={name:"AdminView",components:{}};const h=(0,i.Z)(k,[["render",w]]);var b=h;const g=(0,o._)("p",null,"EventsView",-1);function _(n,e,t,r,a,i){const u=(0,o.up)("router-link"),s=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)(o.HY,null,[g,(0,o._)("nav",null,[(0,o.Wm)(u,{to:{name:"Events"}},{default:(0,o.w5)((()=>[(0,o.Uk)("Все события")])),_:1}),(0,o.Uk)(" | "),(0,o.Wm)(u,{to:{name:"EventAdd"}},{default:(0,o.w5)((()=>[(0,o.Uk)("Добавить событие")])),_:1})]),(0,o.Wm)(s)],64)}t(7658);var E={name:"EventsView",mounted(){Dn.push({name:"EventList"})}};const y=(0,i.Z)(E,[["render",_]]);var T=y;const I=(0,o._)("p",null,"EventListView",-1);function V(n,e,t,r,a,i){const u=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)(o.HY,null,[I,(0,o._)("ul",null,[(0,o._)("li",null,[(0,o.Wm)(u,{to:i.viewLink({id:1})},{default:(0,o.w5)((()=>[(0,o.Uk)("Event 1")])),_:1},8,["to"])]),(0,o._)("li",null,[(0,o.Wm)(u,{to:i.viewLink({id:2})},{default:(0,o.w5)((()=>[(0,o.Uk)("Event 2")])),_:1},8,["to"])]),(0,o._)("li",null,[(0,o.Wm)(u,{to:i.viewLink({id:3})},{default:(0,o.w5)((()=>[(0,o.Uk)("Event 3")])),_:1},8,["to"])])])],64)}var L={name:"EventListView",components:{},methods:{viewLink(n){return{name:"Event",params:{eventId:n.id}}}}};const W=(0,i.Z)(L,[["render",V]]);var A=W,U=t(3577);const Z=(0,o._)("p",null,"EventView",-1);function O(n,e,t,r,a,i){const u=(0,o.up)("router-link"),s=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)(o.HY,null,[Z,(0,o._)("div",null,"Event "+(0,U.zw)(n.$route.params.eventId),1),(0,o._)("nav",null,[(0,o.Wm)(u,{to:{name:"EventModify",params:n.$route.params.eventId}},{default:(0,o.w5)((()=>[(0,o.Uk)("Редактировать")])),_:1},8,["to"]),(0,o.Uk)(" | "),(0,o.Wm)(u,{to:{name:"TaskAdd",params:n.$route.params.eventId}},{default:(0,o.w5)((()=>[(0,o.Uk)("Добавить задание")])),_:1},8,["to"])]),(0,o.Wm)(s)],64)}var j={name:"EventView",components:{},mounted(){Dn.push({name:"TaskList",params:{eventId:this.$route.params.eventId,taskList:[]}})}};const D=(0,i.Z)(j,[["render",O]]);var x=D;const M=(0,o._)("p",null,"EventAddView",-1);function $(n,e,t,r,a,i){const u=(0,o.up)("event-form");return(0,o.wg)(),(0,o.iD)(o.HY,null,[M,(0,o.Wm)(u)],64)}const F={class:"submit-form"},H=(0,o._)("button",{class:"btn btn-success"},"Добавить",-1);function Y(n,e,t,r,a,i){const u=(0,o.up)("input-text-field"),s=(0,o.up)("input-text-area-field");return(0,o.wg)(),(0,o.iD)("div",F,[(0,o.Wm)(u,{name:"name",label:"Название"}),(0,o.Wm)(s,{name:"description",label:"Описание"}),H])}const z={class:"submit-form"},C={class:"form-group"},P={for:"{{name}}"},S=(0,o._)("input",{type:"text",class:"form-control",id:"{{name}}",name:"{{name}}",required:""},null,-1);function q(n,e,t,r,a,i){return(0,o.wg)(),(0,o.iD)("div",z,[(0,o._)("div",C,[(0,o._)("label",P,(0,U.zw)(t.label),1),S])])}var N={name:"InputTextField",props:["name","label"],data:function(){return{}}};const B=(0,i.Z)(N,[["render",q]]);var G=B;const J={class:"submit-form"},K={class:"form-group"},Q={for:"{{name}}"},R=(0,o._)("textarea",{class:"form-control",id:"{{name}}",name:"{{name}}",required:""},null,-1);function X(n,e,t,r,a,i){return(0,o.wg)(),(0,o.iD)("div",J,[(0,o._)("div",K,[(0,o._)("label",Q,(0,U.zw)(t.label),1),R])])}var nn={name:"InputTextAreaField",props:["name","label"],data:function(){return{}}};const en=(0,i.Z)(nn,[["render",X]]);var tn=en,rn={name:"EventForm",components:{InputTextAreaField:tn,InputTextField:G}};const on=(0,i.Z)(rn,[["render",Y]]);var an=on,un={name:"EventAddView",components:{EventForm:an}};const sn=(0,i.Z)(un,[["render",$]]);var ln=sn;const dn=(0,o._)("p",null,"EventModifyView",-1);function cn(n,e,t,r,a,i){const u=(0,o.up)("event-form");return(0,o.wg)(),(0,o.iD)(o.HY,null,[dn,(0,o.Wm)(u)],64)}var mn={name:"EventModifyView",components:{EventForm:an}};const vn=(0,i.Z)(mn,[["render",cn]]);var fn=vn;const pn=(0,o._)("p",null,"TaskListView",-1);function wn(n,e,t,r,a,i){const u=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)(o.HY,null,[pn,(0,o._)("ul",null,[(0,o._)("li",null,[(0,o.Wm)(u,{to:i.viewLink({id:1,eventId:n.$route.params.eventId})},{default:(0,o.w5)((()=>[(0,o.Uk)("Task "+(0,U.zw)(n.$route.params.eventId)+".1",1)])),_:1},8,["to"])]),(0,o._)("li",null,[(0,o.Wm)(u,{to:i.viewLink({id:2,eventId:n.$route.params.eventId})},{default:(0,o.w5)((()=>[(0,o.Uk)("Task "+(0,U.zw)(n.$route.params.eventId)+".2",1)])),_:1},8,["to"])]),(0,o._)("li",null,[(0,o.Wm)(u,{to:i.viewLink({id:3,eventId:n.$route.params.eventId})},{default:(0,o.w5)((()=>[(0,o.Uk)("Task "+(0,U.zw)(n.$route.params.eventId)+".3",1)])),_:1},8,["to"])])])],64)}var kn={name:"TaskListView",components:{},methods:{viewLink(n){return{name:"Task",params:{eventId:n.eventId,taskId:n.id}}}}};const hn=(0,i.Z)(kn,[["render",wn]]);var bn=hn;function gn(n,e,t,r,a,i){return(0,o.wg)(),(0,o.iD)("p",null,"TaskView")}var _n={name:"TaskView"};const En=(0,i.Z)(_n,[["render",gn]]);var yn=En;function Tn(n,e,t,r,a,i){return(0,o.wg)(),(0,o.iD)("p",null,"TaskAddView")}var In={name:"TaskAddView"};const Vn=(0,i.Z)(In,[["render",Tn]]);var Ln=Vn;function Wn(n,e,t,r,a,i){return(0,o.wg)(),(0,o.iD)("p",null,"TaskModifyView")}var An={name:"TaskModifyView"};const Un=(0,i.Z)(An,[["render",Wn]]);var Zn=Un;const On=[{path:"/",component:f},{path:"/admin",name:"Admin",component:b,children:[{path:"events",name:"Events",component:T,children:[{path:"",name:"EventList",component:A},{path:"add",name:"EventAdd",component:ln},{path:":eventId",name:"Event",component:x,children:[{path:"",name:"TaskList",component:bn},{path:"tasks/add",name:"TaskAdd",component:Ln},{path:"tasks/:taskId",name:"Task",component:yn},{path:"tasks/:taskId/modify",name:"TaskModify",component:Zn}]},{path:":eventId/modify",name:"EventModify",component:fn}]}]},{path:"/about",component:()=>t.e(443).then(t.bind(t,7381))}],jn=(0,d.p7)({history:(0,d.r5)(),routes:On});var Dn=jn,xn=t(3907),Mn=(0,xn.MT)({state:{},getters:{},mutations:{},actions:{},modules:{}});t(8877);(0,r.ri)(l).use(Mn).use(Dn).mount("#app")}},e={};function t(r){var o=e[r];if(void 0!==o)return o.exports;var a=e[r]={exports:{}};return n[r](a,a.exports,t),a.exports}t.m=n,function(){var n=[];t.O=function(e,r,o,a){if(!r){var i=1/0;for(d=0;d<n.length;d++){r=n[d][0],o=n[d][1],a=n[d][2];for(var u=!0,s=0;s<r.length;s++)(!1&a||i>=a)&&Object.keys(t.O).every((function(n){return t.O[n](r[s])}))?r.splice(s--,1):(u=!1,a<i&&(i=a));if(u){n.splice(d--,1);var l=o();void 0!==l&&(e=l)}}return e}a=a||0;for(var d=n.length;d>0&&n[d-1][2]>a;d--)n[d]=n[d-1];n[d]=[r,o,a]}}(),function(){t.d=function(n,e){for(var r in e)t.o(e,r)&&!t.o(n,r)&&Object.defineProperty(n,r,{enumerable:!0,get:e[r]})}}(),function(){t.f={},t.e=function(n){return Promise.all(Object.keys(t.f).reduce((function(e,r){return t.f[r](n,e),e}),[]))}}(),function(){t.u=function(n){return"static/js/about.52df5c0c.js"}}(),function(){t.miniCssF=function(n){}}(),function(){t.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(n){if("object"===typeof window)return window}}()}(),function(){t.o=function(n,e){return Object.prototype.hasOwnProperty.call(n,e)}}(),function(){var n={},e="frontend:";t.l=function(r,o,a,i){if(n[r])n[r].push(o);else{var u,s;if(void 0!==a)for(var l=document.getElementsByTagName("script"),d=0;d<l.length;d++){var c=l[d];if(c.getAttribute("src")==r||c.getAttribute("data-webpack")==e+a){u=c;break}}u||(s=!0,u=document.createElement("script"),u.charset="utf-8",u.timeout=120,t.nc&&u.setAttribute("nonce",t.nc),u.setAttribute("data-webpack",e+a),u.src=r),n[r]=[o];var m=function(e,t){u.onerror=u.onload=null,clearTimeout(v);var o=n[r];if(delete n[r],u.parentNode&&u.parentNode.removeChild(u),o&&o.forEach((function(n){return n(t)})),e)return e(t)},v=setTimeout(m.bind(null,void 0,{type:"timeout",target:u}),12e4);u.onerror=m.bind(null,u.onerror),u.onload=m.bind(null,u.onload),s&&document.head.appendChild(u)}}}(),function(){t.r=function(n){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(n,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(n,"__esModule",{value:!0})}}(),function(){t.p="/"}(),function(){var n={143:0};t.f.j=function(e,r){var o=t.o(n,e)?n[e]:void 0;if(0!==o)if(o)r.push(o[2]);else{var a=new Promise((function(t,r){o=n[e]=[t,r]}));r.push(o[2]=a);var i=t.p+t.u(e),u=new Error,s=function(r){if(t.o(n,e)&&(o=n[e],0!==o&&(n[e]=void 0),o)){var a=r&&("load"===r.type?"missing":r.type),i=r&&r.target&&r.target.src;u.message="Loading chunk "+e+" failed.\n("+a+": "+i+")",u.name="ChunkLoadError",u.type=a,u.request=i,o[1](u)}};t.l(i,s,"chunk-"+e,e)}},t.O.j=function(e){return 0===n[e]};var e=function(e,r){var o,a,i=r[0],u=r[1],s=r[2],l=0;if(i.some((function(e){return 0!==n[e]}))){for(o in u)t.o(u,o)&&(t.m[o]=u[o]);if(s)var d=s(t)}for(e&&e(r);l<i.length;l++)a=i[l],t.o(n,a)&&n[a]&&n[a][0](),n[a]=0;return t.O(d)},r=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];r.forEach(e.bind(null,0)),r.push=e.bind(null,r.push.bind(r))}();var r=t.O(void 0,[998],(function(){return t(5795)}));r=t.O(r)})();
//# sourceMappingURL=app.4e9abf8d.js.map