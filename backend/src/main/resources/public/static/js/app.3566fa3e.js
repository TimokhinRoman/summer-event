(function(){"use strict";var n={766:function(n,e,t){var r=t(963),o=t(252);function i(n,e){const t=(0,o.up)("router-view");return(0,o.wg)(),(0,o.j4)(t)}var a=t(744);const u={},c=(0,a.Z)(u,[["render",i]]);var l=c,s=t(201);function d(n,e,t,r,o,i){return" Home View "}var m={name:"HomeView",components:{}};const f=(0,a.Z)(m,[["render",d]]);var p=f;const v=(0,o._)("p",null,"Admin View",-1);function w(n,e,t,r,i,a){const u=(0,o.up)("router-link"),c=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)(o.HY,null,[v,(0,o._)("nav",null,[(0,o.Wm)(u,{to:"/admin"},{default:(0,o.w5)((()=>[(0,o.Uk)("Домой")])),_:1}),(0,o.Uk)(" | "),(0,o.Wm)(u,{to:"/admin/events"},{default:(0,o.w5)((()=>[(0,o.Uk)("События")])),_:1})]),(0,o.Wm)(c)],64)}var b={name:"AdminView",components:{}};const h=(0,a.Z)(b,[["render",w]]);var g=h;const k=(0,o._)("p",null,"EventsView",-1);function _(n,e,t,r,i,a){const u=(0,o.up)("router-link"),c=(0,o.up)("router-view");return(0,o.wg)(),(0,o.iD)(o.HY,null,[k,(0,o._)("nav",null,[(0,o.Wm)(u,{to:"/admin/events"},{default:(0,o.w5)((()=>[(0,o.Uk)("Все события")])),_:1}),(0,o.Uk)(" | "),(0,o.Wm)(u,{to:"/admin/events/add"},{default:(0,o.w5)((()=>[(0,o.Uk)("Добавить событие")])),_:1})]),(0,o.Wm)(c)],64)}var y={name:"EventsView"};const E=(0,a.Z)(y,[["render",_]]);var V=E;const T=(0,o._)("p",null,"EventListView",-1);function W(n,e,t,r,i,a){const u=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)(o.HY,null,[T,(0,o._)("ul",null,[(0,o._)("li",null,[(0,o.Wm)(u,{to:"/admin/events/1"},{default:(0,o.w5)((()=>[(0,o.Uk)("Event 1")])),_:1})]),(0,o._)("li",null,[(0,o.Wm)(u,{to:"/admin/events/2"},{default:(0,o.w5)((()=>[(0,o.Uk)("Event 2")])),_:1})]),(0,o._)("li",null,[(0,o.Wm)(u,{to:"/admin/events/3"},{default:(0,o.w5)((()=>[(0,o.Uk)("Event 3")])),_:1})])])],64)}var O={name:"EventListView",components:{}};const Z=(0,a.Z)(O,[["render",W]]);var j=Z,x=t(577);const A=(0,o._)("p",null,"EventView",-1);function D(n,e,t,r,i,a){const u=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)(o.HY,null,[A,(0,o._)("div",null,"Event "+(0,x.zw)(n.$route.params.eventId),1),(0,o._)("nav",null,[(0,o.Wm)(u,{to:`/admin/events/${n.$route.params.eventId}/modify`},{default:(0,o.w5)((()=>[(0,o.Uk)("Редактировать")])),_:1},8,["to"]),(0,o.Uk)(" | "),(0,o.Wm)(u,{to:`/admin/events/${n.$route.params.eventId}/tasks/add`},{default:(0,o.w5)((()=>[(0,o.Uk)("Добавить задание")])),_:1},8,["to"])])],64)}var U={name:"EventView",components:{}};const F=(0,a.Z)(U,[["render",D]]);var I=F;const H=(0,o._)("p",null,"EventAddView",-1);function M(n,e,t,r,i,a){const u=(0,o.up)("event-form");return(0,o.wg)(),(0,o.iD)(o.HY,null,[H,(0,o.Wm)(u)],64)}const C={class:"submit-form"},P=(0,o._)("button",{class:"btn btn-success"},"Добавить",-1);function Y(n,e,t,r,i,a){const u=(0,o.up)("input-text-field"),c=(0,o.up)("input-text-area-field");return(0,o.wg)(),(0,o.iD)("div",C,[(0,o.Wm)(u,{name:"name",label:"Название"}),(0,o.Wm)(c,{name:"description",label:"Описание"}),P])}const S={class:"submit-form"},$={class:"form-group"},L={for:"{{name}}"},q=(0,o._)("input",{type:"text",class:"form-control",id:"{{name}}",name:"{{name}}",required:""},null,-1);function z(n,e,t,r,i,a){return(0,o.wg)(),(0,o.iD)("div",S,[(0,o._)("div",$,[(0,o._)("label",L,(0,x.zw)(t.label),1),q])])}var N={name:"InputTextField",props:["name","label"],data:function(){return{}}};const B=(0,a.Z)(N,[["render",z]]);var G=B;const J={class:"submit-form"},K={class:"form-group"},Q={for:"{{name}}"},R=(0,o._)("textarea",{class:"form-control",id:"{{name}}",name:"{{name}}",required:""},null,-1);function X(n,e,t,r,i,a){return(0,o.wg)(),(0,o.iD)("div",J,[(0,o._)("div",K,[(0,o._)("label",Q,(0,x.zw)(t.label),1),R])])}var nn={name:"InputTextAreaField",props:["name","label"],data:function(){return{}}};const en=(0,a.Z)(nn,[["render",X]]);var tn=en,rn={name:"EventForm",components:{InputTextAreaField:tn,InputTextField:G}};const on=(0,a.Z)(rn,[["render",Y]]);var an=on,un={name:"EventAddView",components:{EventForm:an}};const cn=(0,a.Z)(un,[["render",M]]);var ln=cn;const sn=(0,o._)("p",null,"EventModifyView",-1);function dn(n,e,t,r,i,a){const u=(0,o.up)("event-form");return(0,o.wg)(),(0,o.iD)(o.HY,null,[sn,(0,o.Wm)(u)],64)}var mn={name:"EventModifyView",components:{EventForm:an}};const fn=(0,a.Z)(mn,[["render",dn]]);var pn=fn;function vn(n,e,t,r,i,a){return(0,o.wg)(),(0,o.iD)("p",null,"TaskView")}var wn={name:"TaskView"};const bn=(0,a.Z)(wn,[["render",vn]]);var hn=bn;function gn(n,e,t,r,i,a){return(0,o.wg)(),(0,o.iD)("p",null,"TaskAddView")}var kn={name:"TaskAddView"};const _n=(0,a.Z)(kn,[["render",gn]]);var yn=_n;function En(n,e,t,r,i,a){return(0,o.wg)(),(0,o.iD)("p",null,"TaskModifyView")}var Vn={name:"TaskModifyView"};const Tn=(0,a.Z)(Vn,[["render",En]]);var Wn=Tn;const On=[{path:"/",component:p},{path:"/admin",component:g,children:[{path:"events",component:V,children:[{path:"",component:j},{path:"add",component:ln},{path:":eventId",component:I,children:[{path:"modify",component:pn},{path:"tasks",children:[{path:"add",component:yn},{path:":taskId",component:hn,children:[{path:"modify",component:Wn}]}]}]}]}]},{path:"/about",component:()=>t.e(443).then(t.bind(t,381))}],Zn=(0,s.p7)({history:(0,s.r5)(),routes:On});var jn=Zn,xn=t(907),An=(0,xn.MT)({state:{},getters:{},mutations:{},actions:{},modules:{}});t(877);(0,r.ri)(l).use(An).use(jn).mount("#app")}},e={};function t(r){var o=e[r];if(void 0!==o)return o.exports;var i=e[r]={exports:{}};return n[r](i,i.exports,t),i.exports}t.m=n,function(){var n=[];t.O=function(e,r,o,i){if(!r){var a=1/0;for(s=0;s<n.length;s++){r=n[s][0],o=n[s][1],i=n[s][2];for(var u=!0,c=0;c<r.length;c++)(!1&i||a>=i)&&Object.keys(t.O).every((function(n){return t.O[n](r[c])}))?r.splice(c--,1):(u=!1,i<a&&(a=i));if(u){n.splice(s--,1);var l=o();void 0!==l&&(e=l)}}return e}i=i||0;for(var s=n.length;s>0&&n[s-1][2]>i;s--)n[s]=n[s-1];n[s]=[r,o,i]}}(),function(){t.d=function(n,e){for(var r in e)t.o(e,r)&&!t.o(n,r)&&Object.defineProperty(n,r,{enumerable:!0,get:e[r]})}}(),function(){t.f={},t.e=function(n){return Promise.all(Object.keys(t.f).reduce((function(e,r){return t.f[r](n,e),e}),[]))}}(),function(){t.u=function(n){return"static/js/about.6a1eb9b4.js"}}(),function(){t.miniCssF=function(n){}}(),function(){t.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(n){if("object"===typeof window)return window}}()}(),function(){t.o=function(n,e){return Object.prototype.hasOwnProperty.call(n,e)}}(),function(){var n={},e="frontend:";t.l=function(r,o,i,a){if(n[r])n[r].push(o);else{var u,c;if(void 0!==i)for(var l=document.getElementsByTagName("script"),s=0;s<l.length;s++){var d=l[s];if(d.getAttribute("src")==r||d.getAttribute("data-webpack")==e+i){u=d;break}}u||(c=!0,u=document.createElement("script"),u.charset="utf-8",u.timeout=120,t.nc&&u.setAttribute("nonce",t.nc),u.setAttribute("data-webpack",e+i),u.src=r),n[r]=[o];var m=function(e,t){u.onerror=u.onload=null,clearTimeout(f);var o=n[r];if(delete n[r],u.parentNode&&u.parentNode.removeChild(u),o&&o.forEach((function(n){return n(t)})),e)return e(t)},f=setTimeout(m.bind(null,void 0,{type:"timeout",target:u}),12e4);u.onerror=m.bind(null,u.onerror),u.onload=m.bind(null,u.onload),c&&document.head.appendChild(u)}}}(),function(){t.r=function(n){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(n,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(n,"__esModule",{value:!0})}}(),function(){t.p="/"}(),function(){var n={143:0};t.f.j=function(e,r){var o=t.o(n,e)?n[e]:void 0;if(0!==o)if(o)r.push(o[2]);else{var i=new Promise((function(t,r){o=n[e]=[t,r]}));r.push(o[2]=i);var a=t.p+t.u(e),u=new Error,c=function(r){if(t.o(n,e)&&(o=n[e],0!==o&&(n[e]=void 0),o)){var i=r&&("load"===r.type?"missing":r.type),a=r&&r.target&&r.target.src;u.message="Loading chunk "+e+" failed.\n("+i+": "+a+")",u.name="ChunkLoadError",u.type=i,u.request=a,o[1](u)}};t.l(a,c,"chunk-"+e,e)}},t.O.j=function(e){return 0===n[e]};var e=function(e,r){var o,i,a=r[0],u=r[1],c=r[2],l=0;if(a.some((function(e){return 0!==n[e]}))){for(o in u)t.o(u,o)&&(t.m[o]=u[o]);if(c)var s=c(t)}for(e&&e(r);l<a.length;l++)i=a[l],t.o(n,i)&&n[i]&&n[i][0](),n[i]=0;return t.O(s)},r=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];r.forEach(e.bind(null,0)),r.push=e.bind(null,r.push.bind(r))}();var r=t.O(void 0,[998],(function(){return t(766)}));r=t.O(r)})();
//# sourceMappingURL=app.3566fa3e.js.map