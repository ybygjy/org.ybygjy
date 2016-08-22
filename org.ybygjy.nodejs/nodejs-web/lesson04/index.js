var superagent = require('superagent');
var cheerio = require('cheerio');
var eventproxy = require('eventproxy');
var url = require('url');

var cnodeurl = 'https://cnodejs.org/';
superagent.get(cnodeurl).end(function(err, res){
    if (err) {
        return console.error(err);
    }
    var topicUrls = [];
    var $ = cheerio.load(res.text);
    $('#topic_list .topic_title').each(function(idx, element){
        var $element = $(element);
        var href = url.resolve(cnodeurl, $element.attr('href'));
        topicUrls.push(href);
    });
    console.log(topicUrls);

    var ep = new eventproxy();
    ep.after('topic_html', topicUrls.length, function(topics){
        topics = topics.map(function(topicPair){
            var topicUrl = topicPair[0];
            var topicHtml = topicPair[1];
            var $ = cheerio.load(topicHtml);
            var title = $('.topic_full_title').text().trim();
            var score = $('.user_card .big').eq(0).html();
            var commentAuthor = $('.reply_author').eq(0).text();
            if (score) {
                console.log(score);
                score = score.trim();
                var regexp = new RegExp(/(\d+)$/);
                var match = regexp.exec(score);
                if (match) {
                    console.log(match[1]);
                    score = match[1];
                }
            }
            return ({
                title:$('.topic_full_title').text().trim(),
                href:topicUrl,
                comment:$('.reply_content').eq(0).text().trim(),
                commentAuthor:commentAuthor,
                score:score
            });
        });
        var tmpResult = [];
        for (var i = 0; i < topics.length; i++) {
            if (topics[i]['title'].length == 0) {
                continue;
            }
            tmpResult.push(topics[i]);
        }
        console.log('final:');
        console.log(tmpResult);
    });
    topicUrls.forEach(function(topicUrl){
        superagent.get(topicUrl).end(function(err, res){
            console.log('fetch ' + topicUrl + ' successful');
            ep.emit('topic_html', [topicUrl, res.text]);
        });
    });
});

