JavaのXMLのパーサーの性能比較をするプログラムです。
Java7が必要です。

SAX
DOM
STAX_C
STAX_E

TYPE=SAX, File:file1.xml
  5705[ms] / avg=570[us]
  3990[ms] / avg=399[us]
  3140[ms] / avg=314[us]
  3151[ms] / avg=315[us]
  3336[ms] / avg=333[us]
  3184[ms] / avg=318[us]
  3155[ms] / avg=315[us]
  3158[ms] / avg=315[us]
  3160[ms] / avg=316[us]
  3169[ms] / avg=316[us]
=====SAX=====
min1           0
max1          36
rmin        3140
rmax        5705
ravg        3514
total      35148
==========

TYPE=DOM, File:file1.xml
  4442[ms] / avg=444[us]
  3328[ms] / avg=332[us]
  3342[ms] / avg=334[us]
  3300[ms] / avg=330[us]
  3303[ms] / avg=330[us]
  3294[ms] / avg=329[us]
  3292[ms] / avg=329[us]
  3291[ms] / avg=329[us]
  3305[ms] / avg=330[us]
  3284[ms] / avg=328[us]
=====DOM=====
min1           0
max1          15
rmin        3284
rmax        4442
ravg        3418
total      34181
==========

TYPE=STAX_C, File:file1.xml
  2611[ms] / avg=261[us]
  2465[ms] / avg=246[us]
  1855[ms] / avg=185[us]
  1839[ms] / avg=183[us]
  1985[ms] / avg=198[us]
  1881[ms] / avg=188[us]
  1839[ms] / avg=183[us]
  1886[ms] / avg=188[us]
  1840[ms] / avg=184[us]
  1842[ms] / avg=184[us]
=====STAX_C=====
min1           0
max1          53
rmin        1839
rmax        2611
ravg        2004
total      20043
==========

TYPE=SAX, File:file2.xml
  5068[ms] / avg=506[us]
  4718[ms] / avg=471[us]
  4775[ms] / avg=477[us]
  4748[ms] / avg=474[us]
  4721[ms] / avg=472[us]
  4728[ms] / avg=472[us]
  4760[ms] / avg=476[us]
  4726[ms] / avg=472[us]
  4724[ms] / avg=472[us]
  4703[ms] / avg=470[us]
=====SAX=====
min1           0
max1          12
rmin        4703
rmax        5068
ravg        4767
total      47671
==========

TYPE=DOM, File:file2.xml
  5129[ms] / avg=512[us]
  5069[ms] / avg=506[us]
  5015[ms] / avg=501[us]
  5430[ms] / avg=543[us]
  5304[ms] / avg=530[us]
  5226[ms] / avg=522[us]
  5124[ms] / avg=512[us]
  5089[ms] / avg=508[us]
  5241[ms] / avg=524[us]
  5047[ms] / avg=504[us]
=====DOM=====
min1           0
max1          13
rmin        5015
rmax        5430
ravg        5167
total      51674
==========

TYPE=STAX_C, File:file2.xml
  3432[ms] / avg=343[us]
  3411[ms] / avg=341[us]
  3417[ms] / avg=341[us]
  3417[ms] / avg=341[us]
  3417[ms] / avg=341[us]
  3518[ms] / avg=351[us]
  3486[ms] / avg=348[us]
  3460[ms] / avg=346[us]
  3471[ms] / avg=347[us]
  3493[ms] / avg=349[us]
=====STAX_C=====
min1           0
max1           5
rmin        3411
rmax        3518
ravg        3452
total      34522
==========

