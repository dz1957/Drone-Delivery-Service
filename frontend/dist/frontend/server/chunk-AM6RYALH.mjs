import './polyfills.server.mjs';
import{D as j,F as W,G as X,H as N,I as b,J as Y,K as P,L as Q,M as B,P as z,V as G,a as p,b as E,c as w,d as I,e as _,f as $,g as D,h as T,i as R,j as O,k as F,l,m as c,n as g,o as M,p as m,q as o,r as h,s as A,t as V,u as q,v as H,w as L}from"./chunk-HHVF53SS.mjs";var U=(()=>{let f=class f{};f.\u0275fac=function(e){return new(e||f)},f.\u0275mod=I({type:f}),f.\u0275inj=E({imports:[P,N,P,N]});let v=f;return v})();function se(v,f){if(v&1&&(l(0,"li"),h(1),c()),v&2){let k=f.$implicit;D(1),V(" Params: ",k.params,", Response: ",k.response," ")}}var J=(()=>{let f=class f{title(r){throw new Error("Method not implemented.")}constructor(r,e){this.http=r,this.cdr=e,this.currentResponse="",this.history=[],this.history$=p([])}createStore(r,e,t,s){if(isNaN(e)||isNaN(t)||isNaN(s)){console.error("All parameters must be valid numbers"),this.currentResponse="All parameters must be valid numbers";return}let n=new b().set("storeName",r).set("initialRevenue",e.toString()).set("x",t.toString()).set("y",s.toString()),i="http://localhost:8080/store";this.http.post(i,null,{responseType:"text",params:n}).subscribe({next:a=>{this.currentResponse=a,this.history=[...this.history,{params:`storeName=${r}, initialRevenue=${e}, x=${t}, y=${s}`,response:a}],console.log(this.history),this.history$=p(this.history),this.cdr.detectChanges()},error:a=>{this.currentResponse=`Failed to create store: ${a.statusText}`,this.history=[...this.history,{params:`storeName=${r}, initialRevenue=${e}, x=${t}, y=${s}`,response:`Error: ${a.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}trackByFn(r,e){return r}displayStores(){let r="http://localhost:8080/stores";this.http.get(r,{responseType:"text"}).subscribe({next:e=>{this.currentResponse=e,this.history.push({params:"Display all stores",response:e}),this.history$=p(this.history),this.cdr.detectChanges()},error:e=>{console.error("Failed to display stores:",e),this.currentResponse=`Failed to display stores: ${e.statusText}`,this.history.push({params:"Display all stores",response:`Error: ${e.statusText}`}),this.history$=p(this.history),this.cdr.detectChanges()}})}sellItem(r,e,t){let s=new b().set("storeName",r).set("itemName",e).set("weight",t),n="http://localhost:8080/sellItem";this.http.post(n,null,{responseType:"text",params:s}).subscribe({next:i=>{this.currentResponse=i,this.history=[...this.history,{params:`Sell Item: storeName=${r}, itemName=${e}, weight=${t}`,response:i}],console.log(this.history),this.history$=p(this.history),this.cdr.detectChanges()},error:i=>{this.currentResponse=`Failed to sell item: ${i.statusText}`,this.history=[...this.history,{params:`Sell Item: storeName=${r}, itemName=${e}, weight=${t}`,response:`Error: ${i.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}displayItems(r){let e=new b().set("storeName",r),t="http://localhost:8080/displayItems";this.http.get(t,{responseType:"text",params:e}).subscribe({next:s=>{this.currentResponse=s,this.history=[...this.history,{params:`Display Items: storeName=${r}`,response:s}],console.log(this.history),this.history$=p(this.history),this.cdr.detectChanges()},error:s=>{this.currentResponse=`Failed to display items: ${s.statusText}`,this.history=[...this.history,{params:`Display Items: storeName=${r}`,response:`Error: ${s.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}makePilot(r,e,t,s,n,i,a){let u=new b().set("pilotAccount",r).set("firstName",e).set("lastName",t).set("phoneNumber",s).set("taxId",n).set("licenseId",i).set("expLevel",a.toString()),d="http://localhost:8080/makePilot";this.http.post(d,null,{responseType:"text",params:u}).subscribe({next:y=>{this.currentResponse=y,this.history=[...this.history,{params:`Pilot Account=${r}, First Name=${e}, Last Name=${t}, Phone Number=${s}, Tax ID=${n}, License ID=${i}, Experience Level=${a}`,response:y}],console.log(this.history),this.history$=p(this.history),this.cdr.detectChanges()},error:y=>{this.currentResponse=`Failed to make pilot: ${y.statusText}`,this.history=[...this.history,{params:`Pilot Account=${r}, First Name=${e}, Last Name=${t}, Phone Number=${s}, Tax ID=${n}, License ID=${i}, Experience Level=${a}`,response:`Error: ${y.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}displayPilots(){let r="http://localhost:8080/displayPilots";this.http.get(r,{responseType:"text"}).subscribe({next:e=>{this.currentResponse=e,this.history.push({params:"Display all pilots",response:e}),console.log(this.history),this.history$=p(this.history)},error:e=>{console.error("Failed to display pilots:",e),this.currentResponse=`Failed to display pilots: ${e.statusText}`,this.history.push({params:"Display all pilots",response:`Error: ${e.statusText}`}),this.history$=p(this.history),this.cdr.detectChanges()}})}makeDrone(r,e,t,s,n,i,a,u,d){let y=new b().set("storeName",r).set("droneId",e).set("weight",t.toString()).set("numOfDlv",s.toString()).set("fuelCapacity",n.toString()).set("fuelRate",i.toString()).set("refuelingRate",a.toString()).set("x",u.toString()).set("y",d.toString()),x="http://localhost:8080/makeDrone";this.http.post(x,null,{responseType:"text",params:y}).subscribe({next:C=>{this.currentResponse=C,this.history=[...this.history,{params:`Make Drone: Store Name=${r}, Drone ID=${e}, Weight=${t}, Number of Deliveries=${s}, Fuel Capacity=${n}, Fuel Rate=${i}, Refueling Rate=${a}, X Coordinate=${u}, Y Coordinate=${d}`,response:C}],console.log(this.history),this.history$=p(this.history),this.cdr.detectChanges()},error:C=>{this.currentResponse=`Failed to make drone: ${C.statusText}`,this.history=[...this.history,{params:`Make Drone: Store Name=${r}, Drone ID=${e}, Weight=${t}, Number of Deliveries=${s}, Fuel Capacity=${n}, Fuel Rate=${i}, Refueling Rate=${a}, X Coordinate=${u}, Y Coordinate=${d}`,response:`Error: ${C.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}flyDrone(r,e,t){let s=new b().set("storeName",r).set("droneId",e).set("pilotId",t),n="http://localhost:8080/flyDrone";this.http.put(n,null,{responseType:"text",params:s}).subscribe({next:i=>{this.currentResponse=i,this.history=[...this.history,{params:`Fly Drone: Store Name=${r}, Drone ID=${e}, Pilot ID=${t}`,response:i}],console.log(this.history),this.history$=p(this.history),this.cdr.detectChanges()},error:i=>{this.currentResponse=`Failed to fly drone: ${i.statusText}`,this.history=[...this.history,{params:`Fly Drone: Store Name=${r}, Drone ID=${e}, Pilot ID=${t}`,response:`Error: ${i.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}displayDrones(r){let e=new b().set("storeName",r),t="http://localhost:8080/displayDrones";this.http.get(t,{responseType:"text",params:e}).subscribe({next:s=>{this.currentResponse=s,this.history.push({params:`Display Drones: Store Name=${r}`,response:s}),console.log(this.history),this.history$=p(this.history),this.cdr.detectChanges()},error:s=>{console.error("Failed to display drones:",s),this.currentResponse=`Failed to display drones: ${s.statusText}`,this.history.push({params:`Display Drones: Store Name=${r}`,response:`Error: ${s.statusText}`}),this.history$=p(this.history),this.cdr.detectChanges()}})}createCustomer(r,e,t,s,n,i,a,u){if(isNaN(n)||isNaN(i)||isNaN(a)||isNaN(u)){console.error("Rating, credit, x, and y must be valid numbers"),this.currentResponse="Rating, credit, x, and y must be valid numbers";return}let d=new b().set("account",r).set("firstName",e).set("lastName",t).set("phoneNumber",s).set("rating",n.toString()).set("credit",i.toString()).set("x",a.toString()).set("y",u.toString()),y="http://localhost:8080/makeCustomer";this.http.post(y,null,{responseType:"text",params:d}).subscribe({next:x=>{this.currentResponse=x,this.history=[...this.history,{params:`account=${r}, firstName=${e}, lastName=${t}, phoneNumber=${s}, rating=${n}, credit=${i}, x=${a}, y=${u}`,response:x}],console.log(this.history),this.history$=p(this.history),this.cdr.detectChanges()},error:x=>{this.currentResponse=`Failed to create customer: ${x.statusText}`,this.history=[...this.history,{params:`account=${r}, firstName=${e}, lastName=${t}, phoneNumber=${s}, rating=${n}, credit=${i}, x=${a}, y=${u}`,response:`Error: ${x.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}displayCustomers(){let r="http://localhost:8080/displayCustomers";this.http.get(r,{responseType:"text"}).subscribe({next:e=>{this.currentResponse=e,this.history.push({params:"Display all customers",response:e}),this.history$=p(this.history),this.cdr.detectChanges()},error:e=>{this.currentResponse=`Failed to display customers: ${e.statusText}`,this.history.push({params:"Display all customers",response:`Error: ${e.statusText}`}),this.history$=p(this.history),this.cdr.detectChanges()}})}startOrder(r,e,t,s){if(isNaN(t)){console.error("Drone ID must be a valid number"),this.currentResponse="Drone ID must be a valid number";return}let n=new b().set("storeName",r).set("orderId",e).set("droneId",t.toString()).set("customerAccount",s),i="http://localhost:8080/startOrder";this.http.post(i,null,{responseType:"text",params:n}).subscribe({next:a=>{this.currentResponse=a,this.history=[...this.history,{params:`storeName=${r}, orderId=${e}, droneId=${t}, customerAccount=${s}`,response:a}],this.history$=p(this.history),this.cdr.detectChanges()},error:a=>{this.currentResponse=`Failed to start order: ${a.statusText}`,this.history=[...this.history,{params:`storeName=${r}, orderId=${e}, droneId=${t}, customerAccount=${s}`,response:`Error: ${a.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}displayOrders(r){let e=new b().set("storeName",r),t="http://localhost:8080/displayOrders";this.http.get(t,{responseType:"text",params:e}).subscribe({next:s=>{this.currentResponse=s,this.history.push({params:`Display orders for storeName=${r}`,response:s}),this.history$=p(this.history),this.cdr.detectChanges()},error:s=>{this.currentResponse=`Failed to display orders: ${s.statusText}`,this.history.push({params:`Display orders for storeName=${r}`,response:`Error: ${s.statusText}`}),this.history$=p(this.history),this.cdr.detectChanges()}})}requestItem(r,e,t,s,n){if(isNaN(s)||isNaN(n)){console.error("Quantity and unit price must be valid numbers"),this.currentResponse="Quantity and unit price must be valid numbers";return}let i=new b().set("storeName",r).set("orderID",e).set("itemId",t).set("quantity",s.toString()).set("unitPrice",n.toString()),a="http://localhost:8080/requestItem";this.http.put(a,null,{responseType:"text",params:i}).subscribe({next:u=>{this.currentResponse=u,this.history=[...this.history,{params:`storeName=${r}, orderID=${e}, itemId=${t}, quantity=${s}, unitPrice=${n}`,response:u}],this.history$=p(this.history),this.cdr.detectChanges()},error:u=>{this.currentResponse=`Failed to request item: ${u.statusText}`,this.history=[...this.history,{params:`storeName=${r}, orderID=${e}, itemId=${t}, quantity=${s}, unitPrice=${n}`,response:`Error: ${u.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}purchaseOrder(r,e){let t=new b().set("storeName",r).set("orderId",e),s="http://localhost:8080/purchaseOrder";this.http.put(s,null,{responseType:"text",params:t}).subscribe({next:n=>{this.currentResponse=n,this.history=[...this.history,{params:`storeName=${r}, orderId=${e}`,response:n}],this.history$=p(this.history),this.cdr.detectChanges()},error:n=>{this.currentResponse=`Failed to purchase order: ${n.statusText}`,this.history=[...this.history,{params:`storeName=${r}, orderId=${e}`,response:`Error: ${n.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}cancelOrder(r,e){let t=new b().set("storeName",r).set("orderId",e),s="http://localhost:8080/cancelOrder";this.http.delete(s,{responseType:"text",params:t}).subscribe({next:n=>{this.currentResponse=n,this.history=[...this.history,{params:`storeName=${r}, orderId=${e}`,response:n}],this.history$=p(this.history),this.cdr.detectChanges()},error:n=>{this.currentResponse=`Failed to cancel order: ${n.statusText}`,this.history=[...this.history,{params:`storeName=${r}, orderId=${e}`,response:`Error: ${n.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}transferOrder(r,e,t){if(isNaN(t)){console.error("New Drone ID must be a valid number"),this.currentResponse="New Drone ID must be a valid number";return}let s=new b().set("storeName",r).set("orderId",e).set("newDroneId",t.toString()),n="http://localhost:8080/transferOrder";this.http.put(n,null,{responseType:"text",params:s}).subscribe({next:i=>{this.currentResponse=i,this.history=[...this.history,{params:`storeName=${r}, orderId=${e}, newDroneId=${t}`,response:i}],this.history$=p(this.history),this.cdr.detectChanges()},error:i=>{this.currentResponse=`Failed to transfer order: ${i.statusText}`,this.history=[...this.history,{params:`storeName=${r}, orderId=${e}, newDroneId=${t}`,response:`Error: ${i.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}displayEfficiency(){let r="http://localhost:8080/displayEfficiency";this.http.get(r,{responseType:"text"}).subscribe({next:e=>{this.currentResponse=e,this.history=[...this.history,{params:"Display efficiency",response:e}],this.history$=p(this.history),this.cdr.detectChanges()},error:e=>{this.currentResponse=`Failed to display efficiency: ${e.statusText}`,this.history=[...this.history,{params:"Display efficiency",response:`Error: ${e.statusText}`}],this.history$=p(this.history),this.cdr.detectChanges()}})}refreshHistory(){this.cdr.detectChanges()}resetHistory(){this.history=[],this.cdr.detectChanges()}};f.\u0275fac=function(e){return new(e||f)(T(Y),T(R))},f.\u0275cmp=w({type:f,selectors:[["app-root"]],standalone:!0,features:[q],decls:189,vars:4,consts:[[1,"container"],[1,"form-section"],["for","storeName"],["type","text","id","storeName","placeholder","storeName"],["storeName",""],["type","number","placeholder","initialValue"],["initialValue",""],["initialX",""],["initialY",""],[3,"click"],["for","itemName"],["type","text","id","storeNameForItem","placeholder","Store Name"],["storeNameForItem",""],["type","text","id","itemName","placeholder","Item Name"],["itemName",""],["type","text","placeholder","Weight"],["weight",""],["for","pilotAccount"],["type","text","id","pilotAccount","placeholder","Pilot Account"],["pilotAccount",""],["type","text","placeholder","First Name"],["firstName",""],["type","text","placeholder","Last Name"],["lastName",""],["type","text","placeholder","Phone Number"],["phoneNumber",""],["type","text","placeholder","Tax ID"],["taxId",""],["type","text","placeholder","License ID"],["licenseId",""],["type","number","placeholder","Experience Level"],["expLevel",""],["for","makeDrone"],["type","text","id","storeNameForDrone","placeholder","Store Name"],["storeNameForDrone",""],["type","text","id","droneId","placeholder","Drone ID"],["droneId",""],["type","number","placeholder","Weight"],["weightForDrone",""],["type","number","placeholder","Number of Deliveries"],["numOfDlv",""],["type","number","placeholder","Fuel Capacity"],["fuelCapacity",""],["type","number","placeholder","Fuel Rate"],["fuelRate",""],["type","number","placeholder","Refueling Rate"],["refuelingRate",""],["type","number","placeholder","X Coordinate"],["xCoordinate",""],["type","number","placeholder","Y Coordinate"],["yCoordinate",""],["for","flyDrone"],["type","text","id","droneIdToFly","placeholder","Drone ID to Fly"],["droneIdToFly",""],["type","text","id","pilotIdToFly","placeholder","Pilot ID to Fly"],["pilotIdToFly",""],["for","customerDetails"],["type","text","id","account","placeholder","Account"],["account",""],["type","number","placeholder","Rating"],["rating",""],["type","number","placeholder","Credit"],["credit",""],["xCoord",""],["yCoord",""],["for","orderDetails"],["type","text","id","storeName","placeholder","Store Name"],["type","text","placeholder","Order ID"],["orderId",""],["type","number","placeholder","Drone ID"],["type","text","placeholder","Customer Account"],["customerAccount",""],["type","text","id","displayStoreName","placeholder","Store Name for Display"],["displayStoreName",""],["for","itemDetails"],["reqStoreName",""],["reqOrderId",""],["type","text","placeholder","Item ID"],["itemId",""],["type","number","placeholder","Quantity"],["quantity",""],["type","number","placeholder","Unit Price"],["unitPrice",""],["for","purchaseCancelDetails"],["type","text","id","pcStoreName","placeholder","Store Name"],["pcStoreName",""],["pcOrderId",""],["for","transferDetails"],["type","text","id","trStoreName","placeholder","Store Name"],["trStoreName",""],["trOrderId",""],["type","number","placeholder","New Drone ID"],["newDroneId",""],["for","efficiencyDetails"],[1,"response-area"],[1,"response-box"],[1,"history-area"],[1,"history-box"],[4,"ngFor","ngForOf"],[1,"bottom-buttons"]],template:function(e,t){if(e&1){let s=M();l(0,"div",0)(1,"h1"),h(2,"Grocery Express Team 30"),c(),l(3,"div",1)(4,"label",2),h(5,"Make and Display Store"),c(),g(6,"input",3,4)(8,"input",5,6)(10,"input",5,7)(12,"input",5,8),l(14,"button",9),m("click",function(){_(s);let i=o(7),a=o(9),u=o(11),d=o(13);return $(t.createStore(i.value,+a.value,+u.value,+d.value))}),h(15,"Create Store"),c(),l(16,"button",9),m("click",function(){return t.displayStores()}),h(17,"Display Stores"),c()(),l(18,"div",1)(19,"label",10),h(20,"Sell and Display Item"),c(),g(21,"input",11,12)(23,"input",13,14)(25,"input",15,16),l(27,"button",9),m("click",function(){_(s);let i=o(22),a=o(24),u=o(26);return $(t.sellItem(i.value,a.value,u.value))}),h(28,"Sell Item"),c(),l(29,"button",9),m("click",function(){_(s);let i=o(7);return $(t.displayItems(i.value))}),h(30,"Display Items"),c()(),l(31,"div",1)(32,"label",17),h(33,"Make and Display Pilots"),c(),g(34,"input",18,19)(36,"input",20,21)(38,"input",22,23)(40,"input",24,25)(42,"input",26,27)(44,"input",28,29)(46,"input",30,31),l(48,"button",9),m("click",function(){_(s);let i=o(35),a=o(37),u=o(39),d=o(41),y=o(43),x=o(45),C=o(47);return $(t.makePilot(i.value,a.value,u.value,d.value,y.value,x.value,+C.value))}),h(49,"Make Pilot"),c(),l(50,"button",9),m("click",function(){return t.displayPilots()}),h(51,"Display Pilots"),c()(),l(52,"div",1)(53,"label",32),h(54,"Make and Display Drone"),c(),g(55,"input",33,34)(57,"input",35,36)(59,"input",37,38)(61,"input",39,40)(63,"input",41,42)(65,"input",43,44)(67,"input",45,46)(69,"input",47,48)(71,"input",49,50),l(73,"button",9),m("click",function(){_(s);let i=o(56),a=o(58),u=o(60),d=o(62),y=o(64),x=o(66),C=o(68),S=o(70),te=o(72);return $(t.makeDrone(i.value,a.value,+u.value,+d.value,+y.value,+x.value,+C.value,+S.value,+te.value))}),h(74,"Make Drone"),c(),l(75,"button",9),m("click",function(){_(s);let i=o(56);return $(t.displayDrones(i.value))}),h(76,"Display Drones"),c()(),l(77,"div",1)(78,"label",51),h(79,"Fly Drone"),c(),g(80,"input",33,34)(82,"input",52,53)(84,"input",54,55),l(86,"button",9),m("click",function(){_(s);let i=o(56),a=o(83),u=o(85);return $(t.flyDrone(i.value,a.value,u.value))}),h(87,"Fly Drone"),c(),l(88,"button",9),m("click",function(){_(s);let i=o(56);return $(t.displayDrones(i.value))}),h(89,"Display Drones"),c()(),l(90,"div",1)(91,"label",56),h(92,"Make and Display Customer"),c(),g(93,"input",57,58)(95,"input",20,21)(97,"input",22,23)(99,"input",24,25)(101,"input",59,60)(103,"input",61,62)(105,"input",47,63)(107,"input",49,64),l(109,"button",9),m("click",function(){_(s);let i=o(94),a=o(37),u=o(39),d=o(41),y=o(102),x=o(104),C=o(106),S=o(108);return $(t.createCustomer(i.value,a.value,u.value,d.value,+y.value,+x.value,+C.value,+S.value))}),h(110,"Create Customer"),c(),l(111,"button",9),m("click",function(){return t.displayCustomers()}),h(112,"Display Customers"),c()(),l(113,"div",1)(114,"label",65),h(115,"Start and Display Order"),c(),g(116,"input",66,4)(118,"input",67,68)(120,"input",69,36)(122,"input",70,71),l(124,"button",9),m("click",function(){_(s);let i=o(7),a=o(119),u=o(58),d=o(123);return $(t.startOrder(i.value,a.value,+u.value,d.value))}),h(125,"Start Order"),c(),g(126,"input",72,73),l(128,"button",9),m("click",function(){_(s);let i=o(127);return $(t.displayOrders(i.value))}),h(129,"Display Orders"),c()(),l(130,"div",1)(131,"label",74),h(132,"Request Item"),c(),g(133,"input",66,75)(135,"input",67,76)(137,"input",77,78)(139,"input",79,80)(141,"input",81,82),l(143,"button",9),m("click",function(){_(s);let i=o(134),a=o(136),u=o(138),d=o(140),y=o(142);return $(t.requestItem(i.value,a.value,u.value,+d.value,+y.value))}),h(144,"Request Item"),c()(),l(145,"div",1)(146,"label",83),h(147,"Purchase and Cancel Order"),c(),g(148,"input",84,85)(150,"input",67,86),l(152,"button",9),m("click",function(){_(s);let i=o(149),a=o(151);return $(t.purchaseOrder(i.value,a.value))}),h(153,"Purchase Order"),c(),l(154,"button",9),m("click",function(){_(s);let i=o(149),a=o(151);return $(t.cancelOrder(i.value,a.value))}),h(155,"Cancel Order"),c()(),l(156,"div",1)(157,"label",87),h(158,"Transfer Order"),c(),g(159,"input",88,89)(161,"input",67,90)(163,"input",91,92),l(165,"button",9),m("click",function(){_(s);let i=o(160),a=o(162),u=o(164);return $(t.transferOrder(i.value,a.value,+u.value))}),h(166,"Transfer Order"),c()(),l(167,"div",1)(168,"label",93),h(169,"Display Efficiency"),c(),l(170,"button",9),m("click",function(){return t.displayEfficiency()}),h(171,"Display Efficiency"),c()(),l(172,"div",94)(173,"label"),h(174,"Current Response"),c(),l(175,"div",95),h(176),c()(),l(177,"div",96)(178,"label"),h(179,"History"),c(),l(180,"div",97)(181,"ul"),F(182,se,2,2,"li",98),H(183,"async"),c()()(),l(184,"div",99)(185,"button",9),m("click",function(){return t.refreshHistory()}),h(186,"Refresh History"),c(),l(187,"button",9),m("click",function(){return t.resetHistory()}),h(188,"Reset"),c()()()}e&2&&(D(176),A(" ",t.currentResponse," "),D(6),O("ngForOf",L(183,2,t.history$)))},dependencies:[U,W,X],styles:["*[_ngcontent-%COMP%]{box-sizing:border-box;margin:0;padding:0}.container[_ngcontent-%COMP%]{max-width:960px;margin:20px auto;padding:20px;font-family:Arial,sans-serif}h1[_ngcontent-%COMP%]{margin-bottom:20px}.form-section[_ngcontent-%COMP%]{display:flex;flex-wrap:wrap;align-items:flex-end;margin-bottom:20px}.form-section[_ngcontent-%COMP%]   label[_ngcontent-%COMP%]{flex-basis:100%;margin-bottom:10px;font-weight:700}.form-section[_ngcontent-%COMP%]   input[type=text][_ngcontent-%COMP%], .form-section[_ngcontent-%COMP%]   input[type=number][_ngcontent-%COMP%]{padding:5px 10px;margin-right:10px;border:1px solid #ccc;border-radius:4px;flex-grow:1}.form-section[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]{padding:5px 10px;background-color:#007bff;color:#fff;border:none;cursor:pointer;transition:background-color .3s ease;margin-bottom:0}.form-section[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]:hover{background-color:#0056b3}.response-area[_ngcontent-%COMP%], .history-area[_ngcontent-%COMP%]{margin-top:20px;padding:10px;border:1px solid #ccc;background-color:#f9f9f9;border-radius:4px}.response-box[_ngcontent-%COMP%], .history-box[_ngcontent-%COMP%]{height:150px;margin-top:10px;padding:10px;border:1px solid #ddd;background-color:#fff;overflow-y:auto}.bottom-buttons[_ngcontent-%COMP%]{display:flex;justify-content:start;gap:10px;margin-top:20px}@media (max-width: 768px){.form-section[_ngcontent-%COMP%]   input[_ngcontent-%COMP%], .form-section[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]{width:100%;margin-right:0}.form-section[_ngcontent-%COMP%], .bottom-buttons[_ngcontent-%COMP%]{flex-direction:column}}.form-section[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]:focus, .form-section[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]:focus{outline:none;border-color:#007bff;box-shadow:0 0 0 .2rem #007bff40}.form-section[_ngcontent-%COMP%]:last-of-type{justify-content:space-between}.form-section[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]:not(:last-child){margin-right:10px}"]});let v=f;return v})();var K=[];var Z={providers:[G(K),B()]};var ie={providers:[z()]},ee=j(Z,ie);var ne=()=>Q(J,ee),Ne=ne;export{Ne as a};