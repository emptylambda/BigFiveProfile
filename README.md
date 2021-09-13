## Misc
One interesting discovery while trying to obtain the result form IPIP_NEO is its result report URL, for example this is the URL to my result: 

https://www.personalitytest.net/cgi-bin/shortipipneo3.cgi?View=85777547289853462285702&VX=VX1&VY=28&VZ=T208&Q1=4&Q2=5&Q3=4&Q4=3&Q5=4&Q6=4&Q7=2&Q8=5&Q9=5&Q10=3&Q11=4&Q12=5&Q13=5&Q14=5&Q15=4&Q16=1&Q17=4&Q18=4&Q19=3&Q20=4&Q21=3&Q22=5&Q23=5&Q24=2&Q25=5&Q26=2&Q27=4&Q28=5&Q29=5&Q30=4&Q31=5&Q32=5&Q33=5&Q34=4&Q35=4&Q36=5&Q37=5&Q38=5&Q39=2&Q40=2&Q41=4&Q42=5&Q43=5&Q44=5&Q45=3&Q46=1&Q47=4&Q48=4&Q49=2&Q50=5&Q51=2&Q52=4&Q53=1&Q54=4&Q55=4&Q56=4&Q57=4&Q58=5&Q59=5&Q60=4&Q61=3&Q62=5&Q63=2&Q64=3&Q65=4&Q66=5&Q67=3&Q68=4&Q69=5&Q70=3&Q71=4&Q72=4&Q73=3&Q74=5&Q75=2&Q76=4&Q77=4&Q78=3&Q79=3&Q80=4&Q81=4&Q82=3&Q83=5&Q84=2&Q85=4&Q86=2&Q87=2&Q88=5&Q89=4&Q90=2&Q91=4&Q92=4&Q93=5&Q94=3&Q95=4&Q96=4&Q97=4&Q98=5&Q99=3&Q100=3&Q101=2&Q102=4&Q103=5&Q104=5&Q105=3&Q106=4&Q107=2&Q108=2&Q109=2&Q110=4&Q111=4&Q112=1&Q113=5&Q114=4&Q115=2&Q116=2&Q117=4&Q118=3&Q119=5&Q120=5

Notice the answers to all 120 questions are directly encoded onto the URL!
One can easily obtain arbitrary result by forging the URL accordingly. 

url = 'https://www.personalitytest.net/cgi-bin/shortipipneo3.cgi?View=85777547289853462285702&VX=VX1&VY=28&VZ=T208'
a = '1'
for q in range(1,120):
  url += "&Q%d=" (q, a)
  
  
With the parameter View being seemingly arbitrary, we do know VX encodes the gender choice (1 Male / 2 Female) and VY encodes the age. VZ encodes the country of user's choice, Taiwan being T208, USA being U222. Country / territory code has its own ISO standard and VZ doesn't comply with any ISO variants (https://www.freeformatter.com/iso-country-list-html-select.html) (https://en.wikipedia.org/wiki/ISO_3166-1)
