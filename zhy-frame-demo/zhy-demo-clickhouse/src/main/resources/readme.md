CREATE TABLE default.test_table (
`id` UInt16,
 `name` String,
 `value` String,
 `create_date` Date,
) ENGINE = MergeTree(create_date, id, 8192)