CREATE TABLE default.sys_log (
 `id` String,
 `sysName` String,
 `level` String,
 `msg` String,
 `thread` String,
 `createDate` DateTime,
 `exeDate` DateTime
) ENGINE = MergeTree()
PARTITION BY toYYYYMM(createDate)
ORDER BY exeDate;