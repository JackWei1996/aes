# aes
aes系统管理手册
aes(Aisino Email System)主要功能是定时自动发送邮件。
如果你有大量的用户需要发送邮件，
而且需要定时发送固定的内容或者附件，
本系统是个不错的选择。

主要功能如下：

1.动态配置发送方(configure.properties文件内)

name	配置发送人名称

email	配置发送人邮箱

pass	配置发送人邮箱密码

sendTime	配置发送时间，教程请看

https://blog.csdn.net/chenyiminnanjing/article/details/78491654

delay	配置延迟扫描邮件时间(毫秒)，可能会有退信，所以需要扫描。-1为不扫描。

maxAdjunctCount	配置最大附件文件数，超过就以压缩包形式发送附件。默认为10。

zipAdjunctDelete	配置以压缩包形式发送附件后,是否删除压缩包。默认为不删除。

allAdjunctDelete	配置发送附件后,是否删除该文件或文件夹下所有的内容。默认为不删除。

