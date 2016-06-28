------------------------------------------------------
-- Export file for user ZYC                         --
-- Created by s^@^w on 2014/11/18, 10:37:20 --
------------------------------------------------------
DROP TABLE CMS_ARTICLE_DATA;
create table CMS_ARTICLE_DATA
(
  id            NUMBER(18) not null,
  content       CLOB default '',
  copyfrom      VARCHAR2(255) default '',
  relation      VARCHAR2(255) default '',
  allow_comment NUMBER(1) default 0 not null
)
;
comment on table CMS_ARTICLE_DATA is '文章详表';
comment on column CMS_ARTICLE_DATA.id
  is '编号';
comment on column CMS_ARTICLE_DATA.content
  is '文章内容';
comment on column CMS_ARTICLE_DATA.copyfrom
  is '文章来源';
comment on column CMS_ARTICLE_DATA.relation
  is '相关文章';
comment on column CMS_ARTICLE_DATA.allow_comment
  is '是否允许评论(默认允许评论)';
alter table CMS_ARTICLE_DATA
  add constraint PK_CMS_ART_DATA_ID primary key (ID);

DROP TABLE CMS_CATEGORY;
create table CMS_CATEGORY
(
  id                  NUMBER(18) not null,
  site_id             NUMBER(18) default 1,
  office_id           NUMBER(18),
  parent_id           NUMBER(18) not null,
  parent_ids          VARCHAR2(2000) default '' not null,
  module              VARCHAR2(20) default '',
  name                VARCHAR2(100) default '' not null,
  image               VARCHAR2(255) default '',
  href                VARCHAR2(255) default '',
  target              VARCHAR2(20) default '',
  description         VARCHAR2(255) default '',
  keywords            VARCHAR2(255) default '',
  sort                NUMBER(10) default 30,
  in_menu             NUMBER(1) default 1,
  in_list             NUMBER(1) default 1,
  show_modes          NUMBER(1) default 0,
  allow_comment       NUMBER(1),
  is_audit            NUMBER(1),
  custom_list_view    VARCHAR2(255) default '',
  custom_content_view VARCHAR2(255) default '',
  create_by           VARCHAR2(64) default '',
  create_date         TIMESTAMP(6),
  update_by           VARCHAR2(64) default '',
  update_date         TIMESTAMP(6),
  remarks             VARCHAR2(255) default '',
  del_flag            NUMBER(1) default 0 not null,
  view_config         CLOB
);
comment on table CMS_CATEGORY is '栏目表';
comment on column CMS_CATEGORY.id
  is '编号';
comment on column CMS_CATEGORY.site_id
  is '站点编号';
comment on column CMS_CATEGORY.office_id
  is '归属机构';
comment on column CMS_CATEGORY.parent_id
  is '父级编号';
comment on column CMS_CATEGORY.parent_ids
  is '所有父级编号';
comment on column CMS_CATEGORY.module
  is '栏目模块';
comment on column CMS_CATEGORY.name
  is '栏目名称';
comment on column CMS_CATEGORY.image
  is '栏目图片';
comment on column CMS_CATEGORY.href
  is '链接';
comment on column CMS_CATEGORY.target
  is '目标';
comment on column CMS_CATEGORY.description
  is '描述';
comment on column CMS_CATEGORY.keywords
  is '关键字';
comment on column CMS_CATEGORY.sort
  is '排序（升序）';
comment on column CMS_CATEGORY.in_menu
  is '是否在导航中显示';
comment on column CMS_CATEGORY.in_list
  is '是否在分类页中显示列表';
comment on column CMS_CATEGORY.show_modes
  is '展现方式';
comment on column CMS_CATEGORY.allow_comment
  is '是否允许评论';
comment on column CMS_CATEGORY.is_audit
  is '是否需要审核';
comment on column CMS_CATEGORY.custom_list_view
  is '自定义列表视图';
comment on column CMS_CATEGORY.custom_content_view
  is '自定义内容视图';
comment on column CMS_CATEGORY.create_by
  is '创建者';
comment on column CMS_CATEGORY.create_date
  is '创建时间';
comment on column CMS_CATEGORY.update_by
  is '更新者';
comment on column CMS_CATEGORY.update_date
  is '更新时间';
comment on column CMS_CATEGORY.remarks
  is '备注信息';
comment on column CMS_CATEGORY.del_flag
  is '删除标记';
comment on column CMS_CATEGORY.view_config
  is '视图配置';
alter table CMS_CATEGORY
  add constraint PK_CAT_ID primary key (ID);
create index CMS_CATEGORY_DEL_FLAG on CMS_CATEGORY (DEL_FLAG);
create index CMS_CATEGORY_MODULE on CMS_CATEGORY (MODULE);
create index CMS_CATEGORY_NAME on CMS_CATEGORY (NAME);
create index CMS_CATEGORY_OFFICE_ID on CMS_CATEGORY (OFFICE_ID);
create index CMS_CATEGORY_PARENT_ID on CMS_CATEGORY (PARENT_ID);
create index CMS_CATEGORY_PARENT_IDS on CMS_CATEGORY (PARENT_IDS);
create index CMS_CATEGORY_SITE_ID on CMS_CATEGORY (SITE_ID);
create index CMS_CATEGORY_SORT on CMS_CATEGORY (SORT);

DROP TABLE CMS_ARTICLE;
create table CMS_ARTICLE
(
  id                  NUMBER(18) not null,
  category_id         NUMBER(18) not null,
  title               VARCHAR2(255) default '' not null,
  link                VARCHAR2(255) default '',
  color               VARCHAR2(50) default '',
  image               VARCHAR2(255) default '',
  keywords            VARCHAR2(255) default '',
  description         VARCHAR2(255) default '',
  weight              NUMBER(10) default 0,
  weight_date         TIMESTAMP(6),
  hits                NUMBER(10) default 0,
  posid               VARCHAR2(10) default '',
  custom_content_view VARCHAR2(255) default '',
  view_config         CLOB,
  create_by           VARCHAR2(64) default '',
  create_date         TIMESTAMP(6),
  update_by           VARCHAR2(64) default '',
  update_date         TIMESTAMP(6),
  remarks             VARCHAR2(255) default '',
  del_flag            NUMBER(1) default 0 not null,
  to_from             NUMBER(1) default 1 not null,
  art_data_id         NUMBER(18) not null
)
;
comment on table CMS_ARTICLE is '文章表';
comment on column CMS_ARTICLE.id
  is '编号';
comment on column CMS_ARTICLE.category_id
  is '栏目编号';
comment on column CMS_ARTICLE.title
  is '标题';
comment on column CMS_ARTICLE.link
  is '文章链接';
comment on column CMS_ARTICLE.color
  is '标题颜色';
comment on column CMS_ARTICLE.image
  is '文章图片';
comment on column CMS_ARTICLE.keywords
  is '关键字';
comment on column CMS_ARTICLE.description
  is '描述、摘要';
comment on column CMS_ARTICLE.weight
  is '权重，越大越靠前';
comment on column CMS_ARTICLE.weight_date
  is '权重期限';
comment on column CMS_ARTICLE.hits
  is '点击数';
comment on column CMS_ARTICLE.posid
  is '推荐位，多选';
comment on column CMS_ARTICLE.custom_content_view
  is '自定义内容视图';
comment on column CMS_ARTICLE.view_config
  is '视图配置';
comment on column CMS_ARTICLE.create_by
  is '创建者';
comment on column CMS_ARTICLE.create_date
  is '创建时间';
comment on column CMS_ARTICLE.update_by
  is '更新者';
comment on column CMS_ARTICLE.update_date
  is '更新时间';
comment on column CMS_ARTICLE.remarks
  is '备注信息';
comment on column CMS_ARTICLE.del_flag
  is '删除标记(0表示正常，1表示假删除)';
comment on column CMS_ARTICLE.to_from
  is '采集出处，默认1。（1官方后台录入，2爬虫抓取，3用户自己录入。）';
comment on column CMS_ARTICLE.art_data_id
  is '文章详情表编号';
alter table CMS_ARTICLE
  add constraint PK_CMS_ART_ID primary key (ID);
alter table CMS_ARTICLE
  add constraint FK_CMS_ART_DATA_ID foreign key (ART_DATA_ID)
  references CMS_ARTICLE_DATA (ID);
alter table CMS_ARTICLE
  add constraint FK_CMS_ART_ID foreign key (CATEGORY_ID)
  references CMS_CATEGORY (ID);
create index CMS_ARTICLE_CATEGORY_ID on CMS_ARTICLE (CATEGORY_ID);
create index CMS_ARTICLE_CREATE_BY on CMS_ARTICLE (CREATE_BY);
create index CMS_ARTICLE_DEL_FLAG on CMS_ARTICLE (DEL_FLAG);
create index CMS_ARTICLE_KEYWORDS on CMS_ARTICLE (KEYWORDS);
create index CMS_ARTICLE_TITLE on CMS_ARTICLE (TITLE);
create index CMS_ARTICLE_UPDATE_DATE on CMS_ARTICLE (UPDATE_DATE);
create index CMS_ARTICLE_WEIGHT on CMS_ARTICLE (WEIGHT);

DROP TABLE CMS_ARTICLE_SOURCE;
create table CMS_ARTICLE_SOURCE
(
  id          NUMBER(18) not null,
  link        VARCHAR2(255) default '',
  title       VARCHAR2(255) default '',
  create_by   VARCHAR2(64) default '',
  create_date TIMESTAMP(6),
  update_by   VARCHAR2(64) default '',
  keywords    VARCHAR2(255) default '',
  news_paging NUMBER(2),
  category_id NUMBER(18) default 1 not null,
  color       VARCHAR2(50) default '',
  image       VARCHAR2(255) default '',
  description VARCHAR2(255) default '',
  remarks     VARCHAR2(255) default '',
  to_from     NUMBER(1) default 1,
  state       NUMBER(1) default 0,
  update_date TIMESTAMP(6) not null,
  del_flag    NUMBER(1) default 0 not null,
  art_data_id NUMBER(18) not null
)
;
comment on table CMS_ARTICLE_SOURCE
  is '爬虫抓取存放元数据表';
comment on column CMS_ARTICLE_SOURCE.id
  is '编号';
comment on column CMS_ARTICLE_SOURCE.link
  is '文章链接';
comment on column CMS_ARTICLE_SOURCE.title
  is '文章标题';
comment on column CMS_ARTICLE_SOURCE.create_by
  is '创建者';
comment on column CMS_ARTICLE_SOURCE.create_date
  is '创建时间';
comment on column CMS_ARTICLE_SOURCE.update_by
  is '更新者';
comment on column CMS_ARTICLE_SOURCE.keywords
  is '关键字';
comment on column CMS_ARTICLE_SOURCE.news_paging
  is '原文章页面页码';
comment on column CMS_ARTICLE_SOURCE.category_id
  is '栏目编号';
comment on column CMS_ARTICLE_SOURCE.color
  is '标题颜色';
comment on column CMS_ARTICLE_SOURCE.image
  is '文章图片';
comment on column CMS_ARTICLE_SOURCE.description
  is '摘要，文章正文简要描述';
comment on column CMS_ARTICLE_SOURCE.remarks
  is '备注信息';
comment on column CMS_ARTICLE_SOURCE.to_from
  is '采集类型，默认是1。1爬虫采集；2官方入库；3用户入库。';
comment on column CMS_ARTICLE_SOURCE.state
  is '审核类型，0表示完成入库操作，1表示审核通过，2表示审核不通过，禁用。';
comment on column CMS_ARTICLE_SOURCE.update_date
  is '更新时间';
comment on column CMS_ARTICLE_SOURCE.del_flag
  is '删除标记(0表示正常，1表示假删除)';
comment on column CMS_ARTICLE_SOURCE.art_data_id
  is '文章详情表编号';
alter table CMS_ARTICLE_SOURCE
  add constraint PK_CMS_ART_SOU_ID primary key (ID);
alter table CMS_ARTICLE_SOURCE
  add constraint FK_CMS_ART_DAT_ID foreign key (ART_DATA_ID)
  references CMS_ARTICLE_DATA (ID);
create index IDX_CMS_CAT_ID on CMS_ARTICLE_SOURCE (CATEGORY_ID);

DROP TABLE CMS_COMMENT;
create table CMS_COMMENT
(
  id            NUMBER(18) not null,
  category_id   NUMBER(18) not null,
  content_id    NUMBER(18) not null,
  title         VARCHAR2(255) default '',
  content       VARCHAR2(255) default '',
  name          VARCHAR2(100) default '',
  ip            VARCHAR2(100) default '',
  create_date   TIMESTAMP(6) not null,
  audit_user_id VARCHAR2(64) default '',
  audit_date    TIMESTAMP(6),
  del_flag      NUMBER(1) default 0 not null
)
;
comment on table CMS_COMMENT
  is '评论表';
comment on column CMS_COMMENT.id
  is '编号';
comment on column CMS_COMMENT.category_id
  is '栏目编号';
comment on column CMS_COMMENT.content_id
  is '文章内容编号';
comment on column CMS_COMMENT.title
  is '栏目内容标题';
comment on column CMS_COMMENT.content
  is '评论内容';
comment on column CMS_COMMENT.name
  is '评论姓名';
comment on column CMS_COMMENT.ip
  is '评论IP';
comment on column CMS_COMMENT.create_date
  is '评论时间';
comment on column CMS_COMMENT.audit_user_id
  is '审核人';
comment on column CMS_COMMENT.audit_date
  is '审核时间';
comment on column CMS_COMMENT.del_flag
  is '删除标记(0表示正常，1表示假删除)';
alter table CMS_COMMENT
  add constraint PK_CMS_COMMENT_ID primary key (ID);
alter table CMS_COMMENT
  add constraint FK_CMS_CAT_ID foreign key (CATEGORY_ID)
  references CMS_CATEGORY (ID);
alter table CMS_COMMENT
  add constraint FK_CMS_CON_ID foreign key (CONTENT_ID)
  references CMS_ARTICLE (ID);
create index CMS_COMMENT_STATUS on CMS_COMMENT (DEL_FLAG);

DROP TABLE CMS_GUESTBOOK;
create table CMS_GUESTBOOK
(
  id          NUMBER(18) not null,
  type        NUMBER(1) not null,
  content     VARCHAR2(255) default '' not null,
  name        VARCHAR2(100) default '' not null,
  email       VARCHAR2(100) default '' not null,
  phone       VARCHAR2(100) default '' not null,
  workunit    VARCHAR2(100) default '' not null,
  ip          VARCHAR2(100) default '' not null,
  create_date TIMESTAMP(6) not null,
  re_user_id  VARCHAR2(64) default '',
  re_date     TIMESTAMP(6),
  re_content  VARCHAR2(100) default '',
  del_flag    NUMBER(1) default 0 not null
)
;
comment on table CMS_GUESTBOOK
  is '留言板';
comment on column CMS_GUESTBOOK.id
  is '编号';
comment on column CMS_GUESTBOOK.type
  is '留言分类';
comment on column CMS_GUESTBOOK.content
  is '留言内容';
comment on column CMS_GUESTBOOK.name
  is '姓名';
comment on column CMS_GUESTBOOK.email
  is '邮箱';
comment on column CMS_GUESTBOOK.phone
  is '电话';
comment on column CMS_GUESTBOOK.workunit
  is '单位';
comment on column CMS_GUESTBOOK.ip
  is 'IP';
comment on column CMS_GUESTBOOK.create_date
  is '留言时间';
comment on column CMS_GUESTBOOK.re_user_id
  is '回复人';
comment on column CMS_GUESTBOOK.re_date
  is '回复时间';
comment on column CMS_GUESTBOOK.re_content
  is '回复内容';
comment on column CMS_GUESTBOOK.del_flag
  is '删除标记(0表示正常，1表示假删除)';
alter table CMS_GUESTBOOK
  add constraint PK_CMS_GUESTBOOK primary key (ID);
create index CMS_GUESTBOOK_DEL_FLAG on CMS_GUESTBOOK (DEL_FLAG);

DROP TABLE CMS_HELP;
create table CMS_HELP
(
  id          NUMBER(18) not null,
  title       VARCHAR2(255) default '',
  state       NUMBER(1) default 0 not null,
  url         VARCHAR2(255) default '',
  art_id      NUMBER(18) default 0,
  create_time TIMESTAMP(6),
  update_time TIMESTAMP(6),
  del_flag    NUMBER(1) default 0 not null
)
;
comment on table CMS_HELP
  is '导航帮助';
comment on column CMS_HELP.id
  is '编号';
comment on column CMS_HELP.title
  is '标题';
comment on column CMS_HELP.state
  is '是否外链(0表示外链,1表示本站编辑)';
comment on column CMS_HELP.url
  is '外链地址';
comment on column CMS_HELP.art_id
  is '本站文章ID';
comment on column CMS_HELP.create_time
  is '创建时间';
comment on column CMS_HELP.update_time
  is '修改时间';
comment on column CMS_HELP.del_flag
  is '删除标记(0表示正常，1表示假删除)';
alter table CMS_HELP
  add constraint PK_CMS_HELP_ID primary key (ID);
alter table CMS_HELP
  add constraint FK_CMS_HELP_ART_ID foreign key (ART_ID)
  references CMS_ARTICLE (ID);

DROP TABLE CMS_LINK;
create table CMS_LINK
(
  id          NUMBER(18) not null,
  type        VARCHAR2(50) default '' not null,
  title       VARCHAR2(20) default '' not null,
  color       VARCHAR2(50) default '',
  image       VARCHAR2(255) default '',
  href        VARCHAR2(255) default '',
  weight      NUMBER(10) default 0,
  weight_date TIMESTAMP(6),
  create_by   VARCHAR2(64) default '',
  create_date TIMESTAMP(6),
  update_by   VARCHAR2(64) default '',
  update_date TIMESTAMP(6),
  remarks     VARCHAR2(255) default '',
  del_flag    NUMBER(18) default 0 not null,
  state       NUMBER(1) default 0 not null
)
;
comment on table CMS_LINK
  is '友情链接';
comment on column CMS_LINK.id
  is '编号';
comment on column CMS_LINK.type
  is '所属范畴';
comment on column CMS_LINK.title
  is '链接名称';
comment on column CMS_LINK.color
  is '标题颜色';
comment on column CMS_LINK.image
  is '链接图片';
comment on column CMS_LINK.href
  is '链接地址';
comment on column CMS_LINK.weight
  is '权重，越大越靠前';
comment on column CMS_LINK.weight_date
  is '权重期限';
comment on column CMS_LINK.create_by
  is '创建者';
comment on column CMS_LINK.create_date
  is '创建时间';
comment on column CMS_LINK.update_by
  is '更新者';
comment on column CMS_LINK.update_date
  is '更新时间';
comment on column CMS_LINK.remarks
  is '备注信息';
comment on column CMS_LINK.del_flag
  is '删除标记(0表示正常，1表示假删除)';
comment on column CMS_LINK.state
  is '文字或图片（0表示纯文字，1表示图片）';
alter table CMS_LINK
  add constraint PK_CMS_LINK primary key (ID);
create index CMS_LINK_CREATE_BY on CMS_LINK (CREATE_BY);
create index CMS_LINK_DEL_FLAG on CMS_LINK (DEL_FLAG);
create index CMS_LINK_TITLE on CMS_LINK (TITLE);
create index CMS_LINK_UPDATE_DATE on CMS_LINK (UPDATE_DATE);
create index CMS_LINK_WEIGHT on CMS_LINK (WEIGHT);

DROP TABLE CMS_SITE;
create table CMS_SITE
(
  id                NUMBER(18) not null,
  name              VARCHAR2(100) default '' not null,
  title             VARCHAR2(100) default '' not null,
  logo              VARCHAR2(255) default '',
  domain            VARCHAR2(255) default '',
  description       VARCHAR2(255) default '',
  keywords          VARCHAR2(255) default '',
  theme             VARCHAR2(255) default 'default',
  copyright         CLOB,
  custom_index_view VARCHAR2(255) default '',
  create_by         VARCHAR2(64) default '',
  create_date       TIMESTAMP(6),
  update_by         VARCHAR2(64) default '',
  update_date       TIMESTAMP(6),
  remarks           VARCHAR2(255) default '',
  del_flag          NUMBER(1) default 0
)
;
comment on table CMS_SITE
  is '站点表';
comment on column CMS_SITE.id
  is '编号';
comment on column CMS_SITE.name
  is '站点名称';
comment on column CMS_SITE.title
  is '站点标题';
comment on column CMS_SITE.logo
  is '站点Logo';
comment on column CMS_SITE.domain
  is '站点域名';
comment on column CMS_SITE.description
  is '描述';
comment on column CMS_SITE.keywords
  is '关键字';
comment on column CMS_SITE.theme
  is '主题';
comment on column CMS_SITE.copyright
  is '版权信息';
comment on column CMS_SITE.custom_index_view
  is '自定义站点首页视图';
comment on column CMS_SITE.create_by
  is '创建者';
comment on column CMS_SITE.create_date
  is '创建时间';
comment on column CMS_SITE.update_by
  is '更新者';
comment on column CMS_SITE.update_date
  is '更新时间';
comment on column CMS_SITE.remarks
  is '备注信息';
comment on column CMS_SITE.del_flag
  is '删除标记(0表示正常，1表示假删除)';
alter table CMS_SITE
  add constraint PK_CMS_SITE primary key (ID);
create index CMS_SITE_DEL_FLAG on CMS_SITE (DEL_FLAG);