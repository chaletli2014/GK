--queryChildrenSubjectInfo
CREATE DEFINER = `root`@`localhost` FUNCTION `queryChildrenSubjectInfo`(subjectId INT)
 RETURNS varchar(4000)
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
 
SET sTemp = '$';
SET sTempChd = cast(subjectId as char);
 
WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT group_concat(id) INTO sTempChd FROM tbl_goods_house_subject where FIND_IN_SET(parentId,sTempChd)>0;
END WHILE;
return sTemp;
END;