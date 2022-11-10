import re

content = """<h1>
    <span property="v:itemreviewed">国境以南，太阳以西</span>
    <div class="clear"></div>
</h1>

        
  <div id="content">
    
    <div class="grid-16-8 clearfix">
      
      <div class="article">



<div class="indent">
  <div class="subjectwrap clearfix">
    
    



<div class="subject clearfix">
<div id="mainpic" class="">

  

  <a class="nbg"
      href="https://img1.doubanio.com/view/subject/l/public/s29808508.jpg" title="国境以南，太阳以西">
      <img src="https://img1.doubanio.com/view/subject/s/public/s29808508.jpg" title="点击看大图" alt="国境以南，太阳以西"
         rel="v:photo" style="max-width: 135px;max-height: 200px;">
  </a>


</div>





<div id="info" class="">



    
    
  
    <span>
      <span class="pl"> 作者</span>:
        
            <a class="" href="/author/4502361">[日]村上春树</a>
    </span><br/>

    
    
  
    <span class="pl">出版社:</span>
      <a href="https://book.douban.com/press/2582">上海译文出版社</a>
    <br>

    
    
  

    
    
  

    
    
  
    <span class="pl">原作名:</span> 国境の南 太陽の西<br/>

    
    
  
    <span>
      <span class="pl"> 译者</span>:
        
            
            <a class="" href="/search/%E6%9E%97%E5%B0%91%E5%8D%8E">林少华</a>
    </span><br/>

    
    
  
    <span class="pl">出版年:</span> 2018-7<br/>

    
    
  
    <span class="pl">页数:</span> 221<br/>

    
    
  
    <span class="pl">定价:</span> 35<br/>

    
    
  
    <span class="pl">装帧:</span> 平装<br/>

    
    
  
    <span class="pl">丛书:</span>&nbsp;<a href="https://book.douban.com/series/42756">村上春树作品系列（2018版）</a><br>

    
    
  
    
      
      <span class="pl">ISBN:</span> 9787532777563<br/>

    
    
  


</div>

</div>





























    
        





<div id="interest_sectl" class="">
  <div class="rating_wrap clearbox" rel="v:rating">"""


def getContent(pattern, content):
    try:
        item = pattern.search(content).group(1)
        return item
    except Exception as e:
        print(e)
        return None


"""
      <span class="pl"> 作者</span>.*?<a.*?>(?P<author>.*?)</a>
    </span><br/>
"""

book_name = re.compile(r'<span property="v:itemreviewed">(?P<book_name>.*?)</span>', re.S)  # 书名
author = re.compile(r'作者</span>.*?<a.*?>(?P<author>.*?)</a>', re.S)  # 作者
publisher = re.compile(r'<span class="pl">出版社.*?<a.*?>(?P<publisher>.*?)</a>', re.S)  # 出版社
produce = re.compile(r'出品方:</span>.*?<a.*?>(?P<produce>.*?)</a>', re.S)  # 出品方
pubTime = re.compile(r'出版年:</span> (?P<pubTime>.*?)<br/>', re.S)  # 出版时间
pageNum = re.compile(r'页数:</span> (?P<pageNum>.*?)<br/>', re.S)  # 页数
price = re.compile(r'定价:</span> (?P<price>.*?)<br/>', re.S)  # 价格
binding = re.compile(r'装帧:</span> (?P<binding>.*?)<br/>', re.S)  # 装帧
ISBN = re.compile(r'<span class="pl">ISBN:</span> (?P<ISBN>\d+)<br/>', re.S)  # ISBN
score = re.compile(r'<strong class="ll rating_num " property="v:average"> (?P<score>.*?) </strong>', re.S)  # 评分
commented = re.compile(r'<span property="v:votes">(?P<commented>.*?)</span>人评价</a>', re.S)  # 评论条数

data = {
    "book_name": getContent(book_name, content),
    "author": getContent(author, content).strip(),
    "publisher": getContent(publisher, content),
    "produce": getContent(produce, content),
    "pubTime": getContent(pubTime, content),
    "pageNum": getContent(pageNum, content),
    "price": getContent(price, content),
    "binding": getContent(binding, content),
    "ISBN": getContent(ISBN, content),
    "category": "",
    "score": getContent(score, content),
    "commented": getContent(commented, content),
    "link": ""

}

print(data)
