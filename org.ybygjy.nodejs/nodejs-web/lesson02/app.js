var express = require('express');
var utility = require('utility');

var app = express();
app.get('/',function(req,rep){
    var q = req.query.q;

    var md5Value = utility.md5(q);
    rep.send(md5Value);
});

app.listen(3000,function(req, rep){
    console.log('app is running at port 3000');
});
