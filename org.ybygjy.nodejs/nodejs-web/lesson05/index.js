var cheerio = require('cheerio');
var urlObj = require('url');
var eventproxy = require('eventproxy');
var async = require('async');
var superagent = require('superagent');

var concurrentCount = 0;
function fetchUrl(url, callback) {
    var startTime = Date.now();
    var rtnObj = new Object();
    concurrentCount++;
    superagent.get(url).end(function(err,sres){
        //if (err) {
        //    console.log(url, err);
        //    return null;//console.error(err); 
        //}
        var $ = cheerio.load(sres.text);
        var title = $('.topic_full_title').text().trim();
        var author = $('.user_name .dark').text().trim();
        var regexp = new RegExp('/\d+$/');
        var score = $('.floor .big').text().trim();
        var match = regexp.exec(score);
        if (match) {
            score = match[1];
        }
        rtnObj = {title:title,author:author,score:score};
        var endTime = Date.now();
        var delay = (endTime - startTime)/1000;
        console.log('现在的并发连接数是：', concurrentCount, '正在抓取的Url是：', url, '耗时：', delay);
        concurrentCount--;
        callback(null,[rtnObj]);
    });
}
var urls = [];
var serviceUrl = 'https://cnodejs.org/';
var eventProxy = new eventproxy();
eventProxy.all('event_html',function(reqData){
    console.log('return result :: ', reqData);
});
function parseUrls(url) {
    var urls = [];
    superagent.get(url).end(function(err, sres){
        if (err) {
            return next(err);
        }
        var $ = cheerio.load(sres.text);
        $('#topic_list .topic_title').each(function(idx, element){
            var $element = $(element);
            var tmpUrl = urlObj.resolve(serviceUrl, $element.attr('href'));
            urls.push(tmpUrl);
        });
        async.mapLimit(urls, 5, function(url, callback){
            fetchUrl(url, callback);
        },function(error, result){
            eventProxy.emit('event_html',result);
        });
    });
}

parseUrls(serviceUrl);
