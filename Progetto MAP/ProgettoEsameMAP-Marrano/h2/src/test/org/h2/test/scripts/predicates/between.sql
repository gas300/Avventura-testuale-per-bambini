-- Copyright 2004-2023 H2 Group. Multiple-Licensed under the MPL 2.0,
-- and the EPL 1.0 (https://h2database.com/html/license.html).
-- Initial Developer: H2 Group
--

CREATE TABLE TEST(ID INT PRIMARY KEY, X INT, A INT, B INT) AS VALUES
    (1, NULL, NULL, NULL),
    (2, NULL, NULL, 1),
    (3, NULL, 1, NULL),
    (4, 1, NULL, NULL),
    (5, NULL, 1, 1),
    (6, NULL, 1, 2),
    (7, NULL, 2, 1),
    (8, 1, NULL, 1),
    (9, 1, NULL, 2),
    (10, 2, NULL, 1),
    (11, 1, 1, NULL),
    (12, 1, 2, NULL),
    (13, 2, 1, NULL),
    (14, 1, 1, 1),
    (15, 1, 1, 2),
    (16, 1, 2, 1),
    (17, 2, 1, 1),
    (18, 1, 2, 2),
    (19, 2, 1, 2),
    (20, 2, 2, 1),
    (21, 1, 2, 3),
    (22, 1, 3, 2),
    (23, 2, 1, 3),
    (24, 2, 3, 1),
    (25, 3, 1, 2),
    (26, 3, 2, 1);
> ok

EXPLAIN SELECT X BETWEEN A AND B A1, X BETWEEN ASYMMETRIC A AND B A2 FROM TEST;
>> SELECT "X" BETWEEN "A" AND "B" AS "A1", "X" BETWEEN "A" AND "B" AS "A2" FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */

EXPLAIN SELECT X BETWEEN SYMMETRIC A AND B S1 FROM TEST;
>> SELECT "X" BETWEEN SYMMETRIC "A" AND "B" AS "S1" FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */

EXPLAIN SELECT X NOT BETWEEN A AND B NA1, X NOT BETWEEN ASYMMETRIC A AND B NA2 FROM TEST;
>> SELECT "X" NOT BETWEEN "A" AND "B" AS "NA1", "X" NOT BETWEEN "A" AND "B" AS "NA2" FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */

EXPLAIN SELECT X NOT BETWEEN SYMMETRIC A AND B NS1 FROM TEST;
>> SELECT "X" NOT BETWEEN SYMMETRIC "A" AND "B" AS "NS1" FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */

SELECT X BETWEEN A AND B A1, X BETWEEN ASYMMETRIC A AND B A2, A <= X AND X <= B A3,
    X BETWEEN SYMMETRIC A AND B S1, A <= X AND X <= B OR A >= X AND X >= B S2,
    X NOT BETWEEN A AND B NA1, X NOT BETWEEN ASYMMETRIC A AND B NA2, NOT (A <= X AND X <= B) NA3,
    X NOT BETWEEN SYMMETRIC A AND B NS1, NOT (A <= X AND X <= B OR A >= X AND X >= B) NS2
    FROM TEST ORDER BY ID;
> A1    A2    A3    S1    S2    NA1   NA2   NA3   NS1   NS2
> ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
> null  null  null  null  null  null  null  null  null  null
> null  null  null  null  null  null  null  null  null  null
> null  null  null  null  null  null  null  null  null  null
> null  null  null  null  null  null  null  null  null  null
> null  null  null  null  null  null  null  null  null  null
> null  null  null  null  null  null  null  null  null  null
> null  null  null  null  null  null  null  null  null  null
> null  null  null  null  null  null  null  null  null  null
> null  null  null  null  null  null  null  null  null  null
> FALSE FALSE FALSE null  null  TRUE  TRUE  TRUE  null  null
> null  null  null  null  null  null  null  null  null  null
> FALSE FALSE FALSE null  null  TRUE  TRUE  TRUE  null  null
> null  null  null  null  null  null  null  null  null  null
> TRUE  TRUE  TRUE  TRUE  TRUE  FALSE FALSE FALSE FALSE FALSE
> TRUE  TRUE  TRUE  TRUE  TRUE  FALSE FALSE FALSE FALSE FALSE
> FALSE FALSE FALSE TRUE  TRUE  TRUE  TRUE  TRUE  FALSE FALSE
> FALSE FALSE FALSE FALSE FALSE TRUE  TRUE  TRUE  TRUE  TRUE
> FALSE FALSE FALSE FALSE FALSE TRUE  TRUE  TRUE  TRUE  TRUE
> TRUE  TRUE  TRUE  TRUE  TRUE  FALSE FALSE FALSE FALSE FALSE
> FALSE FALSE FALSE TRUE  TRUE  TRUE  TRUE  TRUE  FALSE FALSE
> FALSE FALSE FALSE FALSE FALSE TRUE  TRUE  TRUE  TRUE  TRUE
> FALSE FALSE FALSE FALSE FALSE TRUE  TRUE  TRUE  TRUE  TRUE
> TRUE  TRUE  TRUE  TRUE  TRUE  FALSE FALSE FALSE FALSE FALSE
> FALSE FALSE FALSE TRUE  TRUE  TRUE  TRUE  TRUE  FALSE FALSE
> FALSE FALSE FALSE FALSE FALSE TRUE  TRUE  TRUE  TRUE  TRUE
> FALSE FALSE FALSE FALSE FALSE TRUE  TRUE  TRUE  TRUE  TRUE
> rows (ordered): 26

EXPLAIN SELECT * FROM TEST WHERE ID BETWEEN 1 AND 2;
>> SELECT "PUBLIC"."TEST"."ID", "PUBLIC"."TEST"."X", "PUBLIC"."TEST"."A", "PUBLIC"."TEST"."B" FROM "PUBLIC"."TEST" /* PUBLIC.PRIMARY_KEY_2: ID >= 1 AND ID <= 2 */ WHERE "ID" BETWEEN 1 AND 2

EXPLAIN SELECT * FROM TEST WHERE ID NOT BETWEEN 1 AND 2;
>> SELECT "PUBLIC"."TEST"."ID", "PUBLIC"."TEST"."X", "PUBLIC"."TEST"."A", "PUBLIC"."TEST"."B" FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */ WHERE "ID" NOT BETWEEN 1 AND 2

EXPLAIN SELECT NULL BETWEEN A AND B, X BETWEEN NULL AND NULL, X BETWEEN SYMMETRIC A AND NULL, X BETWEEN SYMMETRIC NULL AND B, X BETWEEN SYMMETRIC NULL AND NULL FROM TEST;
>> SELECT UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */

EXPLAIN SELECT X BETWEEN 1 AND 1, X NOT BETWEEN 1 AND 1, 2 BETWEEN SYMMETRIC 3 AND 1 FROM TEST;
>> SELECT "X" = 1, "X" <> 1, TRUE FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */

EXPLAIN SELECT 2 BETWEEN 1 AND B, 2 BETWEEN A AND 3, 2 BETWEEN A AND B FROM TEST;
>> SELECT 2 BETWEEN 1 AND "B", 2 BETWEEN "A" AND 3, 2 BETWEEN "A" AND "B" FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */

EXPLAIN SELECT X BETWEEN 1 AND NULL, X BETWEEN NULL AND 3 FROM TEST;
>> SELECT "X" BETWEEN 1 AND NULL, "X" BETWEEN NULL AND 3 FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */

EXPLAIN SELECT NOT (X BETWEEN A AND B), NOT (X NOT BETWEEN A AND B) FROM TEST;
>> SELECT "X" NOT BETWEEN "A" AND "B", "X" BETWEEN "A" AND "B" FROM "PUBLIC"."TEST" /* PUBLIC.TEST.tableScan */

DROP TABLE TEST;
> ok

SELECT CURRENT_TIME BETWEEN CURRENT_DATE AND (CURRENT_DATE + INTERVAL '1' DAY);
> exception TYPES_ARE_NOT_COMPARABLE_2