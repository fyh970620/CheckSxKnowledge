webpackJsonp([9],{QuBU:function(e,t,a){t=e.exports=a("lcwS")(!1),t.push([e.i,"table[data-v-9ffabeb0]{width:100%;font-size:14px;border:1px solid #eee}table thead th[data-v-9ffabeb0]{background:#e5eff8;padding:22px 18px;text-align:center}table tbody td[data-v-9ffabeb0]{padding:10px 0 10px 18px;text-align:left;border-bottom:1px solid #eee;border-right:1px solid #eee}table span[data-v-9ffabeb0]{margin:0 10px;cursor:pointer}.delete[data-v-9ffabeb0]{color:red}.edit[data-v-9ffabeb0]{color:#008cd5}.add[data-v-9ffabeb0]{border:1px solid #eee;margin-bottom:10px;height:65px;padding:15px 0 15px 15px}tbody tr[data-v-9ffabeb0]:hover{background-color:#e5f0f86c}.add button[data-v-9ffabeb0]{background:#008cd5;border:0;cursor:pointer;padding:6px 15px;border-radius:3px;color:#fff;margin-left:10px}.hello[data-v-9ffabeb0]{margin-top:-10px;margin-bottom:10px}.header-search-input[data-v-9ffabeb0]{display:inline-block;right:1%;margin-left:12px;width:300px}.header-search-input input[data-v-9ffabeb0]{position:relative;background-color:#fff;border-radius:4px;border:1px solid #d8dce5;display:inline-block;height:35px;line-height:1;outline:0;padding:0 25px 0 15px;width:280px;margin-right:-4px}.query-search-btn[data-v-9ffabeb0]{color:#c2d6e7;position:relative;display:inline-block;margin-left:-30px;cursor:pointer}.add-left[data-v-9ffabeb0]{font-size:16px}.hello[data-v-9ffabeb0]{position:fixed;bottom:0;right:20px}.add-right[data-v-9ffabeb0]{margin-right:80px}.leave[data-v-9ffabeb0]{margin-left:50px}.leave span[data-v-9ffabeb0]{color:red}",""])},bK6C:function(e,t,a){"use strict";var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"table1"},[a("div",{staticClass:"add cb"},[a("div",{staticClass:"add-left fl"},[e._v("请假管理")]),e._v(" "),a("div",{staticClass:"add-right fr"},[a("button",{on:{click:function(t){e.dialogFormVisible=!0}}},[e._v("新增")])])]),e._v(" "),a("el-table",{ref:"multipleTable",staticStyle:{width:"100%"},attrs:{data:e.LeaveList,"tooltip-effect":"dark",type:"index"}},[a("el-table-column",{attrs:{type:"selection",width:"70"}}),e._v(" "),a("el-table-column",{attrs:{prop:"staff",label:"请假人","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[e._v(e._s(e._f("showStaffName")(t.row)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"beginLeaveDate",label:"请假开始时间","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[e._v(e._s(t.row.beginLeaveDate))])]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"endLeaveDate",label:"请假结束时间","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[e._v(e._s(t.row.endLeaveDate))])]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"leaveDay",label:"请假天数",width:"110"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[e._v(e._s(t.row.leaveDay))])]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"staffGroupType",label:"归属小组",width:"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[e._v(e._s(t.row.staffGroupType))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:"140"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){e.deletelist(e.LeaveList.staffId,t.$index)}}},[a("i",{staticClass:"el-icon-delete delete"})])]}}])})],1),e._v(" "),a("el-dialog",{staticClass:"main-content",attrs:{title:"新增",visible:e.dialogFormVisible,width:"60%",size:"mini"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"formName",attrs:{"label-position":e.labelPosition,inline:!0,"label-width":"130px",rules:e.rules,model:e.addDetail,size:"mini"}},[a("el-form-item",{attrs:{label:"请假人",prop:"staffname"}},[a("el-input",{attrs:{placeholder:"",name:"createStaffId",disabled:!0},model:{value:e.addDetail.staffname,callback:function(t){e.addDetail.staffname=t},expression:"addDetail.staffname"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"归属组",prop:"staffGroupType"}},[[a("el-select",{attrs:{placeholder:"请选择归属小组"},model:{value:e.addDetail.staffGroupType,callback:function(t){e.addDetail.staffGroupType=t},expression:"addDetail.staffGroupType"}},e._l(e.staffGroupType,function(e){return a("el-option",{key:e.typeId,attrs:{label:e.typeValue,value:e.typeId}})}))]],2),e._v(" "),a("el-form-item",{attrs:{label:"请假开始日期",prop:"beginLeaveDate"}},[[a("el-date-picker",{attrs:{type:"date",placeholder:"选择开始日期","value-format":"yyyy-MM-dd"},model:{value:e.addDetail.beginLeaveDate,callback:function(t){e.addDetail.beginLeaveDate=t},expression:"addDetail.beginLeaveDate"}})]],2),e._v(" "),a("el-form-item",{attrs:{label:"请假结束日期",prop:"endLeaveDate"}},[[a("el-date-picker",{attrs:{type:"date",placeholder:"选择结束日期","value-format":"yyyy-MM-dd"},model:{value:e.addDetail.endLeaveDate,callback:function(t){e.addDetail.endLeaveDate=t},expression:"addDetail.endLeaveDate"}})]],2),e._v(" "),a("div",{staticClass:"leave"},[e._v("一共请假了"),a("span",[e._v(e._s(e.leaveDays))]),e._v("天")])],1),e._v(" "),a("span",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(t){e.adddetail(e.addDetail,"formName")}}},[e._v("新增")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")])],1)],1),e._v(" "),a("div",{staticClass:"hello"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[5,10],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.pageSizeChange,"current-change":e.currentPageChange}})],1)],1)},l=[],o={render:i,staticRenderFns:l};t.a=o},dNRU:function(e,t,a){var i=a("QuBU");"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);a("X/Wc")("6894c382",i,!0,{})},f557:function(e,t,a){"use strict";function i(e){a("dNRU")}Object.defineProperty(t,"__esModule",{value:!0});var l=a("i79W"),o=a.n(l);for(var n in l)"default"!==n&&function(e){a.d(t,e,function(){return l[e]})}(n);var d=a("bK6C"),s=a("/4AN"),r=i,f=s(o.a,d.a,!1,r,"data-v-9ffabeb0",null);t.default=f.exports},i79W:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=a("CxL4"),l=function(e){return e&&e.__esModule?e:{default:e}}(i);t.default={name:"table1",data:function(){return{rules:{beginLeaveDate:[{required:!0,message:"请选择请假开始日期",trigger:"blur"}],endLeaveDate:[{required:!0,message:"请选择请假结束日期",trigger:"blur"}],staffGroupType:[{required:!0,message:"请选择归属小组",trigger:"blur"}]},dialogVisible:!1,dialogFormVisible:!1,addDetail:{beginLeaveDate:"",endLeaveDate:"",staffId:"",staffGroupType:"",beginDate:"",leaveDay:""},staffId:"",staffGroupType:"",beginDate:"",editDetail:{},index:0,LeaveList:[],subjectName:"",staffname:"",staffID:"",editid:"",multipleSelection:[],currentPage:1,pageSize:10,total:""}},mounted:function(){this.getselect(),this.getAauthor()},filters:{showStaffName:function(e){return e.staff?e.staff.staffname:""}},computed:{leaveDays:function(){return 0==this.addDetail.endLeaveDate.length||0==this.addDetail.beginLeaveDate.length?"0":parseInt((new Date(this.addDetail.endLeaveDate.replace(/-/g,"/")).getTime()-new Date(this.addDetail.beginLeaveDate.replace(/-/g,"/")).getTime())/864e5)}},methods:{getselect:function(){var e=this;l.default.getListGroupType().then(function(t){e.staffGroupType=t.data}).catch(function(e){console.log(e)})},getAauthor:function(){var e=this;l.default.getListSxByGType().then(function(t){console.log(t),e.Aauthor=t.data[4],console.log(e.Aauthor),e.staffname=e.Aauthor.staffname,e.staffID=e.Aauthor.staffId,e.getList(),e.addDetail={staffname:e.staffname,beginLeaveDate:"",endLeaveDate:"",leaveDay:"",staffGroupType:""}}).catch(function(e){console.log(e)})},getList:function(){var e=this,t={pages:this.currentPage,pageSize:this.pageSize,staffId:this.staffID};console.log(t),l.default.getStaffLeaveList(t).then(function(t){console.log(t),e.LeaveList=t.data.list,e.total=t.data.total,console.log(e.LeaveList)}).catch(function(e){console.log(e)})},deletelist:function(e,t){var a=this;this.$confirm("确认删除？").then(function(e){var i={leaveId:a.LeaveList[t].leaveId};console.log(i),l.default.delOneCsl(i).then(function(e){console.log(e),a.getAauthor()}).catch(function(e){console.log(e),alert("删除失败！")})}).catch(function(e){})},adddetail:function(e){var t=this;this.addDetail.beginLeaveDate.substring(0,7)==this.addDetail.endLeaveDate.substring(0,7)?this.addDetail.beginLeaveDate.substring(8,10)<this.addDetail.endLeaveDate.substring(8,10)?this.$refs.formName.validate(function(e){if(!e)return!1;var a=t.addDetail.beginLeaveDate;console.log(t.addDetail.beginLeaveDate),a=new Date(a.replace(/-/g,"/"));var i=t.addDetail.endLeaveDate;i=new Date(i.replace(/-/g,"/"));var o=i.getTime()-a.getTime();t.addDetail.leaveDay=parseInt(o/864e5),console.log(t.addDetail.leaveDay),t.addDetail={leaveDay:t.addDetail.leaveDay,beginLeaveDate:t.addDetail.beginLeaveDate,endLeaveDate:t.addDetail.endLeaveDate,staffGroupType:t.addDetail.staffGroupType,checkStaffId:t.staffID};var n={csl:t.addDetail};console.log(n),l.default.addOneCsl(n).then(function(e){alert("submit!"),t.getAauthor()}).catch(function(e){console.log(e)}),t.dialogFormVisible=!1}):console.log("请选择正确的日期"):console.log("不能跨月请假")},pageSizeChange:function(e){console.log("每页 "+e+" 条"),this.pageSize=e,this.getList()},currentPageChange:function(e){this.currentPage=e,console.log("当前页: "+e),this.getList()}}}}});
//# sourceMappingURL=9.197531ce.js.map