GAddMessages({});
__gjsload_maps2_api__('stats', 'if(window.gmapstiming){window.gmapstiming.yw={};window.gmapstiming.BP=1;var ry=function(a,b,c){var d=a.t[b],f=a.t.start;if(d&&(f||c)){d=a.t[b][0];f=c!=undefined?c:f[0];return d-f}}; window.gmapstiming.getTick=ry;window.gmapstiming.getLabels=function(a){var b=[];for(var c in a.t)b.push(c);return b}; window.gmapstiming.setTimerName=function(a,b){a.name=b}; window.gmapstiming.report=function(a,b,c){var d="";if(window.gmapstiming.pt){d+="&srt="+window.gmapstiming.pt;delete window.gmapstiming.pt}try{if(window.external&&window.external.tran)d+="&tran="+window.external.tran;else if(window.gtbExternal&&window.gtbExternal.tran)d+="&tran="+window.gtbExternal.tran();else if(window.chrome&&window.chrome.csi)d+="&tran="+window.chrome.csi().tran}catch(f){}var g=document.getElementById("csi");if(g){var h;if(window.gmapstiming._bfr!=undefined)h=window.gmapstiming._bfr; else{h=g.value;window.gmapstiming._bfr=h;g.value=1}h&&(d+="&bfr=1")}if(g=window.chrome)if(g=g.loadTimes){if(g().wasFetchedViaSpdy)d+="&p=s";if(g().wasNpnNegotiated)d+="&npn=1"}if(a.Ui)d+="&"+a.Ui;g=a.t;h=g.start;var k=[],n=[];for(var p in g)if(p!="start")if(p.indexOf("_")!=0){var q=g[p][1];if(q)g[q]&&n.push(p+"."+ry(a,p,g[q][0]));else h&&k.push(p+"."+ry(a,p))}delete g.start;if(b)for(var u in b)d+="&"+u+"="+b[u];a=[c?c:"http://csi.gstatic.com/csi","?v=3","&s="+(window.gmapstiming.sn||"mfeundefined")+ "&action=",a.name,n.length?"&it="+n.join(","):"","",d,"&rt=",k.join(",")].join("");b=new Image;var E=window.gmapstiming.BP++;window.gmapstiming.yw[E]=b;b.onload=b.onerror=function(){delete window.gmapstiming.yw[E]}; b.src=a;b=i;return a}};var sy="mfe",ty=i,uy=[];function vy(a,b,c){var d=tb||"http://gg."+_mDomain+"/csi",f={};if(ty)f.e=ty;me(c)||(f.cad=mg(c));var g=new window.gmapstiming.Timer(1);lc(b,function(h,k){k[0]!="start"&&g.tick(k[0],i,k[1]+1)}); window.gmapstiming.setTimerName(g,a);window.gmapstiming.sn=sy;window.gmapstiming.report(g,f,d)} function wy(a){var b=new Image,c=xy++;yy[c]=b;b.onload=b.onerror=function(){delete yy[c]}; b.src=a;b=i} var yy={},xy=0;function zy(a,b,c){vy(a,b,c);if(a=="application"||a=="vpage"||a=="vpage-history"||a=="msrender"||a=="msserver"||a=="mymaps")li(Ay(a,b))} function Ay(a,b){var c=_mUri+"/l",d=[],f={};t(b,function(g){d.push(g[0]+"."+g[1])}); f.stat_m=a+":"+d.join(",");return c+Qf(f,e)} function By(a,b){var c={};if(!me(a)){le(c,a);c.oi="jsaction";c.sa="T"}if(!me(b)){var d=[];lc(b,function(f,g){d.push([f,g].join(ha))}); if(s(d)>0){d.unshift("jsaction");c.imp=d.join(ia)}}return _mHost+"/maps/gen_204"+Qf(c,e)} ;T("stats",1,function(a){if(a=="application"||a=="application_link"||a=="apiboot"||a=="details_page"||a=="embed"||a=="textview_results"||a=="placepage"||a=="print"){zy.apply(i,arguments);t(uy,function(b){zy.apply(i,b)}); uy=i}else uy?uy.push(we(arguments)):zy.apply(i,arguments)}); T("stats",ib,function(a,b){wy(By(a,b))}); T("stats",kb,function(a){ty=a}); T("stats",lb,function(a){sy=a}); T("stats",5,function(a,b,c,d){var f=["/maps/dcrt"];f.push("?cookie="+a);f.push("&start="+b);f.push("&end="+c);f.push("&name=maps."+d);wy(f.join(""))}); T("stats",6,wy);T("stats");');