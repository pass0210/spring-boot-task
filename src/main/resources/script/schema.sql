CREATE TABLE `comment` (
                           `commentId`	bigint	NOT NULL,
                           `taskId`	bigint	NOT NULL,
                           `userId`	varchar(30)	NULL,
                           `content`	varchar(500)	NULL
);

CREATE TABLE `tag` (
                       `tagId`	bigint	NOT NULL,
                       `tagName`	varchar(80)	NULL,
                       `projectId`	bigint	NOT NULL
);

CREATE TABLE `task` (
                        `taskId`	bigint	NOT NULL,
                        `projectId`	bigint	NOT NULL,
                        `milestoneId`	bigint	NOT NULL,
                        `taskState`	varchar(10)	NULL,
                        `userId`	varchar(30)	NULL,
                        `title`	varchar(50)	NULL,
                        `content`	varcahr(500)	NULL,
                        `deadline`	datetime	NULL
);

CREATE TABLE `milestone` (
                             `milestoneId`	bigint	NOT NULL,
                             `stepName`	varchar(50)	NULL,
                             `startDate`	datetime	NULL,
                             `endDate`	datetime	NULL,
                             `projectId`	bigint	NOT NULL
);

CREATE TABLE `project` (
                           `projectId`	bigint	NOT NULL,
                           `projectState`	varchar(10)	NULL,
                           `userId`	varchar(30)	NULL,
                           `projectName`	varchar(10)	NULL
);

CREATE TABLE `member` (
                          `memberId`	varchar(30)	NULL,
                          `projectId`	bigint	NOT NULL
);

CREATE TABLE `project_status` (
    `projectState`	varchar(10)	NULL
);

CREATE TABLE `task_status` (
    `taskState`	varchar(10)	NULL
);

CREATE TABLE `task_tag` (
                            `taskId`	bigint	NOT NULL,
                            `tagId`	bigint	NOT NULL
);

ALTER TABLE `comment` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (
                                                               `commentId`
    );

ALTER TABLE `tag` ADD CONSTRAINT `PK_TAG` PRIMARY KEY (
                                                       `tagId`
    );

ALTER TABLE `task` ADD CONSTRAINT `PK_TASK` PRIMARY KEY (
                                                         `taskId`
    );

ALTER TABLE `milestone` ADD CONSTRAINT `PK_MILESTONE` PRIMARY KEY (
                                                                   `milestoneId`
    );

ALTER TABLE `project` ADD CONSTRAINT `PK_PROJECT` PRIMARY KEY (
                                                               `projectId`
    );

ALTER TABLE `member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
                                                             `memberId`,
                                                             `projectId`
    );

ALTER TABLE `project_status` ADD CONSTRAINT `PK_PROJECT_STATUS` PRIMARY KEY (
                                                                             `projectState`
    );

ALTER TABLE `task_status` ADD CONSTRAINT `PK_TASK_STATUS` PRIMARY KEY (
                                                                       `taskState`
    );

ALTER TABLE `task_tag` ADD CONSTRAINT `PK_TASK_TAG` PRIMARY KEY (
                                                                 `taskId`,
                                                                 `tagId`
    );

ALTER TABLE `comment` ADD CONSTRAINT `FK_task_TO_comment_1` FOREIGN KEY (
                                                                         `taskId`
    )
    REFERENCES `task` (
                       `taskId`
        );

ALTER TABLE `tag` ADD CONSTRAINT `FK_project_TO_tag_1` FOREIGN KEY (
                                                                    `projectId`
    )
    REFERENCES `project` (
                          `projectId`
        );

ALTER TABLE `task` ADD CONSTRAINT `FK_project_TO_task_1` FOREIGN KEY (
                                                                      `projectId`
    )
    REFERENCES `project` (
                          `projectId`
        );

ALTER TABLE `task` ADD CONSTRAINT `FK_milestone_TO_task_1` FOREIGN KEY (
                                                                        `milestoneId`
    )
    REFERENCES `milestone` (
                            `milestoneId`
        );

ALTER TABLE `task` ADD CONSTRAINT `FK_task_status_TO_task_1` FOREIGN KEY (
                                                                          `taskState`
    )
    REFERENCES `task_status` (
                              `taskState`
        );

ALTER TABLE `milestone` ADD CONSTRAINT `FK_project_TO_milestone_1` FOREIGN KEY (
                                                                                `projectId`
    )
    REFERENCES `project` (
                          `projectId`
        );

ALTER TABLE `project` ADD CONSTRAINT `FK_project_status_TO_project_1` FOREIGN KEY (
                                                                                   `projectState`
    )
    REFERENCES `project_status` (
                                 `projectState`
        );

ALTER TABLE `member` ADD CONSTRAINT `FK_project_TO_member_1` FOREIGN KEY (
                                                                          `projectId`
    )
    REFERENCES `project` (
                          `projectId`
        );

ALTER TABLE `task_tag` ADD CONSTRAINT `FK_task_TO_task_tag_1` FOREIGN KEY (
                                                                           `taskId`
    )
    REFERENCES `task` (
                       `taskId`
        );

ALTER TABLE `task_tag` ADD CONSTRAINT `FK_tag_TO_task_tag_1` FOREIGN KEY (
                                                                          `tagId`
    )
    REFERENCES `tag` (
                      `tagId`
        );

