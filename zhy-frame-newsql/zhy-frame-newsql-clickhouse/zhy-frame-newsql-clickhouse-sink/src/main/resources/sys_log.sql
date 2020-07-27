CREATE TABLE default.sys_log (
 `id` String,
 `sys_name` String,
 `level` String,
 `msg` String,
 `thread` String,
 `create_date` DateTime,
 `exe_date` DateTime
) ENGINE = MergeTree()
PARTITION BY toYYYYMM(create_date)
ORDER BY exe_date;