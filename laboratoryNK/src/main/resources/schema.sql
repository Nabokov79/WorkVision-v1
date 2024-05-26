CREATE TABLE IF NOT EXISTS LABORATORY_EMPLOYEES
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    employee_id BIGINT                                  NOT NULL,
    post        VARCHAR                                 NOT NULL,
    initials    VARCHAR                                 NOT NULL,
    CONSTRAINT pk_laboratoryEmployee PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS EMPLOYEE_CERTIFICATES
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type      VARCHAR                                 NOT NULL,
    certificate_number VARCHAR                                 NOT NULL,
    control_type       VARCHAR                                 NOT NULL,
    level              VARCHAR                                 NOT NULL,
    start_date         DATE                                    NOT NULL,
    end_date           DATE                                    NOT NULL,
    points             VARCHAR                                 NOT NULL,
    organization       VARCHAR                                 NOT NULL,
    employee_id        BIGINT                                  NOT NULL,
    CONSTRAINT pk_employeeCertificate PRIMARY KEY (id),
    CONSTRAINT UQ_EMPLOYEE_CERTIFICATES UNIQUE (control_type, employee_id),
    CONSTRAINT FK_EMPLOYEE_CERTIFICATES_ON_LABORATORY_EMPLOYEES
        FOREIGN KEY (employee_id) REFERENCES laboratory_employees (id)
);

CREATE TABLE IF NOT EXISTS SURVEY_JOURNALS
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    date                DATE                                    NOT NULL,
    branch              VARCHAR                                 NOT NULL,
    heat_supply_area    VARCHAR,
    exploitation_region VARCHAR,
    building            VARCHAR                                 NOT NULL,
    equipment_id        BIGINT                                  NOT NULL,
    equipment_diagnosed VARCHAR                                 NOT NULL,
    work_type           VARCHAR                                 NOT NULL,
    task_source         VARCHAR,
    comment             VARCHAR,
    chief_id            BIGINT                                  NOT NULL,
    CONSTRAINT pk_surveyJournal PRIMARY KEY (id),
    CONSTRAINT FK_SURVEY_JOURNALS_ON_LABORATORY_EMPLOYEES FOREIGN KEY (chief_id) REFERENCES laboratory_employees (id)
);

CREATE TABLE IF NOT EXISTS JOURNAL_EMPLOYEES
(
    journal_id  BIGINT,
    employee_id BIGINT,
    CONSTRAINT pk_tasks_journal_of_laboratory_employees PRIMARY KEY (journal_id, employee_id)
);

CREATE TABLE IF NOT EXISTS MEASURING_TOOLS
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    toll                   VARCHAR                                 NOT NULL,
    model                  VARCHAR                                 NOT NULL,
    work_number            VARCHAR,
    purpose                VARCHAR,
    manufacturing          DATE,
    exploitation           DATE,
    manufacturer           VARCHAR,
    measuring_range        VARCHAR,
    characteristics        VARCHAR,
    owner                  VARCHAR                                 NOT NULL,
    verification_date      DATE,
    next_verification_date DATE,
    organization           VARCHAR,
    certificate_number     VARCHAR,
    registry               VARCHAR,
    note                   VARCHAR,
    control_type           VARCHAR                                 NOT NULL,
    laboratory_employee_id BIGINT,
    CONSTRAINT pk_measuringTool PRIMARY KEY (id),
    CONSTRAINT UQ_MEASURING_TOOL UNIQUE (toll, model, work_number)
);

CREATE TABLE IF NOT EXISTS DOCUMENTATIONS
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    view   VARCHAR,
    number VARCHAR,
    title  VARCHAR                                 NOT NULL,
    path   VARCHAR,
    CONSTRAINT pk_documentation PRIMARY KEY (id),
    CONSTRAINT UQ_DOCUMENTATIONS UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS LABORATORY_CERTIFICATES
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    division_id    BIGINT                                  NOT NULL,
    document_type  VARCHAR                                 NOT NULL,
    license_number VARCHAR                                 NOT NULL,
    start_date     DATE                                    NOT NULL,
    end_date       DATE                                    NOT NULL,
    organization   VARCHAR                                 NOT NULL,
    CONSTRAINT pk_laboratoryCertificate PRIMARY KEY (id),
    CONSTRAINT UQ_LABORATORY_CERTIFICATES UNIQUE (document_type, license_number, start_date, end_date)
);

CREATE TABLE IF NOT EXISTS DIAGNOSTIC_DOCUMENT_TYPES
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title         VARCHAR                                 NOT NULL,
    subtitle      VARCHAR                                 NOT NULL,
    type_document VARCHAR                                 NOT NULL,
    CONSTRAINT pk_diagnosticDocumentType PRIMARY KEY (id),
    CONSTRAINT UQ_DIAGNOSTIC_DOCUMENT_TYPES UNIQUE (title, subtitle, type_document)
);

CREATE TABLE IF NOT EXISTS DIAGNOSTIC_DOCUMENTS
(
    id                          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id           BIGINT                                  NOT NULL,
    equipment_diagnosed         VARCHAR                                 NOT NULL,
    equipment_diagnosed_id      BIGINT                                  NOT NULL,
    diagnostic_document_type    VARCHAR                                 NOT NULL,
    diagnostic_document_type_id BIGINT                                  NOT NULL,
    date                        DATE                                    NOT NULL,
    installation_location       VARCHAR                                 NOT NULL,
    next_date                   DATE,
    document_number             INTEGER                                 NOT NULL,
    document_status             VARCHAR                                 NOT NULL,
    drawing_status              VARCHAR                                 NOT NULL,
    document_path               VARCHAR,
    drawing_path                VARCHAR,
    CONSTRAINT pk_diagnosticDocument PRIMARY KEY (id),
    CONSTRAINT UQ_DIAGNOSTIC_DOCUMENT UNIQUE (equipment_diagnosed_id, date),
    CONSTRAINT FK_DIAGNOSTIC_DOCUMENT_ON_DIAGNOSTIC_DOCUMENT_TYPES
        FOREIGN KEY (diagnostic_document_type_id) REFERENCES diagnostic_document_types (id)
);

CREATE TABLE IF NOT EXISTS REMARKS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    remark             VARCHAR                                 NOT NULL,
    document_id        BIGINT                                  NOT NULL,
    employee_id        BIGINT                                  NOT NULL,
    initials           VARCHAR                                 NOT NULL,
    document_corrected BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_remark PRIMARY KEY (id),
    CONSTRAINT FK_REMARKS_ON_DIAGNOSTIC_DOCUMENTS FOREIGN KEY (document_id) REFERENCES diagnostic_documents (id)
);

CREATE TABLE IF NOT EXISTS CONCLUSION_TEMPLATES
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type_id BIGINT                                  NOT NULL,
    positive_text    VARCHAR                                 NOT NULL,
    negative_text    VARCHAR                                 NOT NULL,
    CONSTRAINT pk_conclusionTemplate PRIMARY KEY (id),
    CONSTRAINT UQ_CONCLUSION_TEMPLATES UNIQUE (document_type_id)
);

CREATE TABLE IF NOT EXISTS DOCUMENT_HEADERS_TEMPLATES
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type_id BIGINT                                  NOT NULL,
    division_type    VARCHAR                                 NOT NULL,
    division         VARCHAR                                 NOT NULL,
    address          VARCHAR,
    certificate      VARCHAR,
    contacts         VARCHAR,
    CONSTRAINT pk_documentHeaderTemplate PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PAGE_TITLE_TEMPLATES
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type_id      BIGINT                                  NOT NULL,
    equipment_type_id     BIGINT                                  NOT NULL,
    title                 VARCHAR                                 NOT NULL,
    subtitle              VARCHAR                                 NOT NULL,
    equipment_text        VARCHAR                                 NOT NULL,
    installation_location VARCHAR                                 NOT NULL,
    city                  VARCHAR                                 NOT NULL,
    CONSTRAINT pk_pageTitleTemplate PRIMARY KEY (id),
    CONSTRAINT UQ_PAGE_TITLE_TEMPLATES UNIQUE (document_type_id, equipment_type_id)
);

CREATE TABLE IF NOT EXISTS PAGE_TITLE_TEMPLATES_DOCUMENT_HEADERS
(
    page_title_template_id BIGINT,
    document_header_id     BIGINT,
    CONSTRAINT pk_page_title_templates_of_document_header PRIMARY KEY (page_title_template_id, document_header_id)
);

CREATE TABLE IF NOT EXISTS REPORT_TEMPLATES
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type_id  BIGINT                                  NOT NULL,
    equipment_type_id BIGINT                                  NOT NULL,
    page_title_id     BIGINT                                  NOT NULL,
    CONSTRAINT pk_reportTemplate PRIMARY KEY (id),
    CONSTRAINT UQ_REPORT_TEMPLATES UNIQUE (document_type_id, equipment_type_id),
    CONSTRAINT FK_REPORT_TEMPLATES_ON_PAGE_TITLE_TEMPLATES
        FOREIGN KEY (page_title_id) REFERENCES page_title_templates (id)
);

CREATE TABLE IF NOT EXISTS SECTION_TEMPLATES
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type_id   BIGINT                                  NOT NULL,
    equipment_type_id  BIGINT                                  NOT NULL,
    sequential_number  INTEGER                                 NOT NULL,
    section_name       VARCHAR                                 NOT NULL,
    specify_passport   BOOLEAN                                 NOT NULL,
    report_template_id BIGINT,
    CONSTRAINT pk_sectionTemplate PRIMARY KEY (id),
    CONSTRAINT UQ_SECTION_TEMPLATES UNIQUE (document_type_id, equipment_type_id, section_name),
    CONSTRAINT FK_SECTION_TEMPLATES_ON_REPORT_TEMPLATES
        FOREIGN KEY (report_template_id) REFERENCES report_templates (id)
);

CREATE TABLE IF NOT EXISTS REPORT_PROTOCOL_TEMPLATES
(
    id                       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type_id         BIGINT                                  NOT NULL,
    sequential_number        INTEGER                                 NOT NULL,
    title                    VARCHAR                                 NOT NULL,
    subtitle                 VARCHAR                                 NOT NULL,
    user_text_after_subtitle VARCHAR,
    section_template_id      BIGINT,
    conclusion_template_id   BIGINT,
    CONSTRAINT pk_reportProtocolTemplate PRIMARY KEY (id),
    CONSTRAINT FK_REPORT_PROTOCOL_TEMPLATES_ON_SECTION_TEMPLATES
        FOREIGN KEY (section_template_id) REFERENCES section_templates (id),
    CONSTRAINT FK_REPORT_PROTOCOL_TEMPLATES_ON_CONCLUSION_TEMPLATES
        FOREIGN KEY (conclusion_template_id) REFERENCES conclusion_templates (id)
);

CREATE TABLE IF NOT EXISTS SURVEY_PROTOCOL_TEMPLATES
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type_id       BIGINT                                  NOT NULL,
    equipment_type_id      BIGINT                                  NOT NULL,
    title                  VARCHAR                                 NOT NULL,
    subtitle               VARCHAR                                 NOT NULL,
    conclusion_template_id BIGINT,
    CONSTRAINT pk_surveyProtocolTemplate PRIMARY KEY (id),
    CONSTRAINT UQ_SURVEY_PROTOCOL_TEMPLATES UNIQUE (document_type_id, equipment_type_id),
    CONSTRAINT FK_SURVEY_PROTOCOL_TEMPLATES_ON_CONCLUSION_TEMPLATES
        FOREIGN KEY (conclusion_template_id) REFERENCES conclusion_templates (id)
);

CREATE TABLE IF NOT EXISTS SURVEY_PROTOCOL_DOCUMENT_HEADERS_TEMPLATES
(
    protocol_template_id BIGINT,
    document_header_id   BIGINT,
    CONSTRAINT pk_protocol_templates_of_document_header PRIMARY KEY (protocol_template_id, document_header_id)
);

CREATE TABLE IF NOT EXISTS PROTOCOL_CONTROL_TEMPLATES
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type_id BIGINT                                  NOT NULL,
    title            VARCHAR                                 NOT NULL,
    subtitle         VARCHAR                                 NOT NULL,
    CONSTRAINT pk_protocolControlTemplate PRIMARY KEY (id),
    CONSTRAINT UQ_PROTOCOL_CONTROL_TEMPLATES UNIQUE (document_type_id)
);

CREATE TABLE IF NOT EXISTS PROTOCOL_CONTROL_TEMPLATES_DOCUMENT_HEADERS
(
    protocol_template_id BIGINT,
    document_header_id   BIGINT,
    CONSTRAINT pk_protocol_control_templates_of_document_header PRIMARY KEY (protocol_template_id, document_header_id)
);

CREATE TABLE IF NOT EXISTS TABLE_TEMPLATES
(
    id                           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    table_type                   VARCHAR                                 NOT NULL,
    sequential_number            INTEGER,
    table_name                   VARCHAR,
    text_before_table            VARCHAR,
    text_after_table             VARCHAR,
    protocol_report_template_id  BIGINT,
    protocol_template_id         BIGINT,
    protocol_control_template_id BIGINT,
    CONSTRAINT pk_tableTemplate PRIMARY KEY (id),
    CONSTRAINT FK_TABLE_TEMPLATES_ON_REPORT_PROTOCOL_TEMPLATES
        FOREIGN KEY (protocol_report_template_id) REFERENCES report_protocol_templates (id),
    CONSTRAINT FK_TABLE_TEMPLATES_ON_SURVEY_PROTOCOL_TEMPLATES
        FOREIGN KEY (protocol_template_id) REFERENCES survey_protocol_templates (id),
    CONSTRAINT FK_TABLE_TEMPLATES_ON_PROTOCOL_CONTROL_TEMPLATES
        FOREIGN KEY (protocol_control_template_id) REFERENCES protocol_control_templates (id)
);

CREATE TABLE IF NOT EXISTS COLUMNS_HEADERS_TEMPLATES
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    sequential_number  INTEGER                                 NOT NULL,
    heading            VARCHAR                                 NOT NULL,
    column_header_type VARCHAR                                 NOT NULL,
    table_template_id  BIGINT                                  NOT NULL,
    CONSTRAINT pk_columnHeader PRIMARY KEY (id),
    CONSTRAINT UQ_COLUMNS_HEADERS_TEMPLATES UNIQUE (sequential_number, heading, table_template_id),
    CONSTRAINT FK_COLUMNS_HEADERS_TEMPLATES_ON_TABLE_TEMPLATES
        FOREIGN KEY (table_template_id) REFERENCES table_templates (id)
);

CREATE TABLE IF NOT EXISTS SUBSECTION_TEMPLATES
(
    id                           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    sequential_number            DOUBLE PRECISION                        NOT NULL,
    subsection_name              VARCHAR                                 NOT NULL,
    user_text                    VARCHAR,
    division                     VARCHAR,
    table_id                     BIGINT,
    protocol_report_template_id  BIGINT,
    section_template_id          BIGINT,
    protocol_template_id         BIGINT,
    protocol_control_template_id BIGINT,
    CONSTRAINT pk_subsectionTemplate PRIMARY KEY (id),
    CONSTRAINT FK_SUBSECTION_TEMPLATES_ON_TABLE_TEMPLATES FOREIGN KEY (table_id) REFERENCES table_templates (id),
    CONSTRAINT FK_SUBSECTION_TEMPLATES_ON_REPORT_PROTOCOL_TEMPLATES
        FOREIGN KEY (protocol_report_template_id) REFERENCES report_protocol_templates (id),
    CONSTRAINT FK_SUBSECTION_TEMPLATES_ON_SECTION_TEMPLATES
        FOREIGN KEY (section_template_id) REFERENCES section_templates (id),
    CONSTRAINT FK_SUBSECTION_TEMPLATES_ON_SURVEY_PROTOCOL_TEMPLATES
        FOREIGN KEY (protocol_template_id) REFERENCES survey_protocol_templates (id),
    CONSTRAINT FK_SUBSECTION_TEMPLATES_ON_PROTOCOL_CONTROL_TEMPLATES
        FOREIGN KEY (protocol_control_template_id) REFERENCES protocol_control_templates (id)
);

CREATE TABLE IF NOT EXISTS DOCUMENTATION_TEMPLATES
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id      BIGINT                                  NOT NULL,
    documentation_id       BIGINT                                  NOT NULL,
    sequential_number      INTEGER,
    document_name          VARCHAR                                 NOT NULL,
    subsection_template_id BIGINT,
    CONSTRAINT pk_documentationTemplate PRIMARY KEY (id),
    CONSTRAINT FK_DOCUMENTATION_TEMPLATES_ON_SUBSECTION_TEMPLATES
        FOREIGN KEY (subsection_template_id) REFERENCES subsection_templates (id)
);

CREATE TABLE IF NOT EXISTS MEASURING_TOOL_TEMPLATES
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    measuring_tool_id      BIGINT                                  NOT NULL,
    sequential_number      INTEGER,
    toll                   VARCHAR                                 NOT NULL,
    model                  VARCHAR                                 NOT NULL,
    measuring_tool         VARCHAR                                 NOT NULL,
    subsection_template_id BIGINT,
    CONSTRAINT pk_measuringToolTemplate PRIMARY KEY (id),
    CONSTRAINT FK_MEASURING_TOOL_TEMPLATES_ON_SUBSECTION_TEMPLATES
        FOREIGN KEY (subsection_template_id) REFERENCES subsection_templates (id)
);

CREATE TABLE IF NOT EXISTS REPORT_PROTOCOL_SUBSECTION_TEMPLATES
(
    protocol_id   BIGINT,
    subsection_id BIGINT,
    CONSTRAINT pk_report_protocol_templates_of_subsection_templates PRIMARY KEY (protocol_id, subsection_id)
);

CREATE TABLE IF NOT EXISTS REPORT_PROTOCOL_TABLE_TEMPLATES
(
    protocol_id BIGINT,
    table_id    BIGINT,
    CONSTRAINT pk_report_protocol_templates_of_table_templates PRIMARY KEY (protocol_id, table_id)
);

CREATE TABLE IF NOT EXISTS APPENDICES_TEMPLATES
(
    id                   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id    BIGINT                                  NOT NULL,
    name_of_list         VARCHAR                                 NOT NULL,
    sequential_number    INTEGER                                 NOT NULL,
    appendices_name      VARCHAR                                 NOT NULL,
    report_template_id   BIGINT,
    protocol_template_id BIGINT,
    CONSTRAINT pk_appendicesTemplate PRIMARY KEY (id),
    CONSTRAINT UQ_APPENDICES_TEMPLATES UNIQUE (sequential_number, appendices_name),
    CONSTRAINT FK_APPENDICES_TEMPLATES_ON_REPORT_TEMPLATES
        FOREIGN KEY (report_template_id) REFERENCES report_templates (id),
    CONSTRAINT FK_APPENDICES_TEMPLATES_ON_SURVEY_PROTOCOL_TEMPLATES
        FOREIGN KEY (protocol_template_id) REFERENCES survey_protocol_templates (id)
);

CREATE TABLE IF NOT EXISTS PAGE_TITLE
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title                 VARCHAR                                 NOT NULL,
    subtitle              VARCHAR                                 NOT NULL,
    equipment_text        VARCHAR                                 NOT NULL,
    installation_location VARCHAR                                 NOT NULL,
    address               VARCHAR                                 NOT NULL,
    number_date           VARCHAR                                 NOT NULL,
    post                  VARCHAR                                 NOT NULL,
    initials              VARCHAR                                 NOT NULL,
    city                  VARCHAR                                 NOT NULL,
    year                  VARCHAR                                 NOT NULL,
    CONSTRAINT pk_pageTitle PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS DOCUMENT_HEADERS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type_id BIGINT                                  NOT NULL,
    division_type    VARCHAR                                 NOT NULL,
    division         VARCHAR                                 NOT NULL,
    address          VARCHAR,
    certificate      VARCHAR,
    contacts         VARCHAR,
    page_title_id    BIGINT,
    CONSTRAINT pk_documentHeader PRIMARY KEY (id),
    CONSTRAINT FK_DOCUMENT_HEADERS_ON_PAGE_TITLE FOREIGN KEY (page_title_id) REFERENCES page_title (id)
);

CREATE TABLE IF NOT EXISTS EQUIPMENT_TYPES
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_name VARCHAR                                 NOT NULL,
    model          VARCHAR,
    CONSTRAINT pk_equipmentType PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS EQUIPMENT_DIAGNOSED
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id BIGINT                                  NOT NULL,
    building_id       BIGINT                                  NOT NULL,
    equipment_name    VARCHAR                                 NOT NULL,
    stationary_number INTEGER,
    model             VARCHAR,
    volume            INTEGER,
    equipment_full    BOOLEAN,
    old               BOOLEAN,
    CONSTRAINT pk_equipmentDiagnosed PRIMARY KEY (id),
    CONSTRAINT FK_EQUIPMENT_DIAGNOSED_ON_EQUIPMENT_TYPES FOREIGN KEY (equipment_type_id) REFERENCES equipment_types (id)
);

CREATE TABLE IF NOT EXISTS EQUIPMENT_PASSPORT
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_diagnosed_id BIGINT                                  NOT NULL,
    sequential_number      INTEGER                                 NOT NULL,
    header                 VARCHAR                                 NOT NULL,
    meaning                VARCHAR                                 NOT NULL,
    use_to_protocol        BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_equipmentPassport PRIMARY KEY (id),
    CONSTRAINT FK_EQUIPMENT_PASSPORT_ON_EQUIPMENT_DIAGNOSED
        FOREIGN KEY (equipment_diagnosed_id) REFERENCES equipment_diagnosed (id)
);

CREATE TABLE IF NOT EXISTS STANDARD_SIZES
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    design_thickness DOUBLE PRECISION,
    outer_diameter   INTEGER,
    min_diameter     INTEGER,
    max_diameter     INTEGER,
    CONSTRAINT pk_standardSize PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS EQUIPMENT_ELEMENTS
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    element_name           VARCHAR                                 NOT NULL,
    standard_size_id       BIGINT,
    equipment_diagnosed_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_equipmentElement PRIMARY KEY (id),
    CONSTRAINT FK_EQUIPMENT_ELEMENTS_ON_STANDARD_SIZES FOREIGN KEY (standard_size_id) REFERENCES standard_sizes (id),
    CONSTRAINT FK_EQUIPMENT_ELEMENTS_ON_EQUIPMENT_DIAGNOSED
        FOREIGN KEY (equipment_diagnosed_id) REFERENCES equipment_diagnosed (id)
);

CREATE TABLE IF NOT EXISTS PARTS_ELEMENTS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    part_name        VARCHAR                                 NOT NULL,
    element_id       BIGINT                                  NOT NULL,
    standard_size_id BIGINT,
    CONSTRAINT pk_partElement PRIMARY KEY (id),
    CONSTRAINT FK_PARTS_ELEMENTS_ON_STANDARD_SIZES FOREIGN KEY (standard_size_id) REFERENCES standard_sizes (id),
    CONSTRAINT FK_PARTS_ELEMENTS_ON_ELEMENTS FOREIGN KEY (element_id) REFERENCES equipment_elements (id)
);


CREATE TABLE IF NOT EXISTS ACCEPTABLE_DEVIATIONS_GEODESY
(
    id                                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id                 BIGINT                                  NOT NULL,
    fulls                             BOOLEAN                                 NOT NULL,
    old                               BOOLEAN                                 NOT NULL,
    volume                            INTEGER                                 NOT NULL,
    acceptable_precipitation          INTEGER                                 NOT NULL,
    max_difference_neighboring_points INTEGER                                 NOT NULL,
    max_difference_diametric_points   INTEGER                                 NOT NULL,
    measurement_error                 INTEGER                                 NOT NULL,
    number_locations                  INTEGER                                 NOT NULL,
    CONSTRAINT pk_permissibleDeviationsGeodesy PRIMARY KEY (id),
    CONSTRAINT UQ_PERMISSIBLE_DEVIATIONS_GEODESY UNIQUE (equipment_type_id, fulls, old, number_locations)
);

CREATE TABLE IF NOT EXISTS ACCEPTABLE_THICKNESS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id  BIGINT                                  NOT NULL,
    element_id         BIGINT                                  NOT NULL,
    part_element_id    BIGINT,
    diameter           INTEGER,
    min_thickness      DOUBLE PRECISION,
    acceptable_percent INTEGER,
    measurement_error  FLOAT,
    CONSTRAINT pk_acceptableThickness PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ACCEPTABLE_HARDNESS
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_type_id      BIGINT                                  NOT NULL,
    element_id             BIGINT                                  NOT NULL,
    part_element_id        BIGINT,
    min_allowable_diameter INTEGER                                 NOT NULL,
    min_hardness           INTEGER                                 NOT NULL,
    max_hardness           INTEGER                                 NOT NULL,
    measurement_error      FLOAT,
    CONSTRAINT pk_acceptableHardness PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS DEFECTS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    defect_name             VARCHAR                                 NOT NULL,
    not_meet_requirements   BOOLEAN                                 NOT NULL,
    type_calculation        VARCHAR                                 NOT NULL,
    use_calculate_thickness BOOLEAN,
    CONSTRAINT pk_defect PRIMARY KEY (id),
    CONSTRAINT UQ_DEFECTS UNIQUE (defect_name)
);

CREATE TABLE IF NOT EXISTS ELEMENT_REPAIRS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    repair_name      VARCHAR                                 NOT NULL,
    type_calculation VARCHAR                                 NOT NULL,
    CONSTRAINT pk_elementRepair PRIMARY KEY (id),
    CONSTRAINT UQ_ELEMENT_REPAIRS UNIQUE (repair_name)
);

CREATE TABLE IF NOT EXISTS MEASUREMENTS_PARAMETERS
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    parameter_name   VARCHAR                                 NOT NULL,
    unit_measurement VARCHAR                                 NOT NULL,
    type_calculation VARCHAR                                 NOT NULL,
    defect_id        BIGINT,
    repair_id        BIGINT,
    CONSTRAINT pk_measuredParameter PRIMARY KEY (id),
    CONSTRAINT FK_MEASUREMENTS_PARAMETERS_ON_DEFECTS FOREIGN KEY (defect_id) REFERENCES defects (id),
    CONSTRAINT FK_MEASUREMENTS_PARAMETERS_ON_ELEMENT_REPAIRS FOREIGN KEY (repair_id) REFERENCES element_repairs (id)
);

CREATE TABLE IF NOT EXISTS EMPLOYEE_RECOMMENDATIONS
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_id        BIGINT                                  NOT NULL,
    recommendation_text VARCHAR                                 NOT NULL,
    CONSTRAINT pk_employeeRecommendation PRIMARY KEY (id),
    CONSTRAINT UQ_EMPLOYEE_RECOMMENDATIONS UNIQUE (equipment_id, recommendation_text)
);

CREATE TABLE IF NOT EXISTS EQUIPMENT_INSPECTIONS
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_diagnosed_id BIGINT                                  NOT NULL,
    date                   VARCHAR                                 NOT NULL,
    inspection             VARCHAR                                 NOT NULL,
    document_number        VARCHAR                                 NOT NULL,
    organization           VARCHAR                                 NOT NULL,
    CONSTRAINT pk_equipmentInspection PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS EQUIPMENT_REPAIRS
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_diagnosed_id BIGINT                                  NOT NULL,
    date                   VARCHAR                                 NOT NULL,
    description            VARCHAR                                 NOT NULL,
    organization           VARCHAR,
    CONSTRAINT pk_equipmentRepair PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS DATA_EQUIPMENT_CALCULATIONS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id BIGINT                                  NOT NULL,
    equipment_type_id BIGINT                                  NOT NULL,
    equipment_id      BIGINT                                  NOT NULL,
    equipment_full    BOOLEAN,
    equipment_old     BOOLEAN,
    volume            INTEGER,
    model             VARCHAR,
    CONSTRAINT pk_dataOfEquipmentForCalculations PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS STANDARD_SIZES
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    equipment_id     BIGINT                                  NOT NULL,
    element_id       BIGINT                                  NOT NULL,
    part_element_id  BIGINT,
    design_thickness DOUBLE PRECISION,
    outer_diameter   INTEGER,
    min_diameter     INTEGER,
    max_diameter     INTEGER,
    CONSTRAINT pk_standardSize PRIMARY KEY (id),
    CONSTRAINT FK_STANDARD_SIZES_ON_DATA_EQUIPMENT_CALCULATIONS
        FOREIGN KEY (equipment_id) REFERENCES data_equipment_calculations (id)
);

CREATE TABLE IF NOT EXISTS GEODESIC_MEASUREMENTS
(
    id                          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id           BIGINT                                  NOT NULL,
    equipment_id                BIGINT                                  NOT NULL,
    sequential_number           INTEGER                                 NOT NULL,
    number_measurement_location INTEGER                                 NOT NULL,
    reference_point_value       INTEGER,
    control_point_value         INTEGER                                 NOT NULL,
    transition_value            INTEGER,
    CONSTRAINT pk_geodesicMeasurement PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS REFERENCE_POINTS
(
    id                       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id        BIGINT                                  NOT NULL,
    equipment_id             BIGINT                                  NOT NULL,
    place_number             INTEGER                                 NOT NULL,
    calculated_height        INTEGER                                 NOT NULL,
    deviation                INTEGER                                 NOT NULL,
    precipitation            INTEGER,
    acceptable_precipitation BOOLEAN,
    result_measurement_id    BIGINT                                  NOT NULL,
    CONSTRAINT pk_referencePoint PRIMARY KEY (id),
    CONSTRAINT UQ_REFERENCE_POINTS UNIQUE (survey_journal_id, equipment_id, place_number)
);

CREATE TABLE IF NOT EXISTS DEVIATION_YEARS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    reference_point_id BIGINT                                  NOT NULL,
    year               INTEGER                                 NOT NULL,
    deviation          INTEGER                                 NOT NULL,
    CONSTRAINT pk_deviationYear PRIMARY KEY (id),
    CONSTRAINT UQ_DEVIATION_YEARS UNIQUE (reference_point_id, year),
    CONSTRAINT FK_DEVIATION_YEARS_ON_REFERENCE_POINTS FOREIGN KEY (reference_point_id) REFERENCES reference_points (id)
);

CREATE TABLE IF NOT EXISTS CONTROL_POINTS
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id     BIGINT                                  NOT NULL,
    equipment_id          BIGINT                                  NOT NULL,
    place_number          INTEGER                                 NOT NULL,
    calculated_height     INTEGER                                 NOT NULL,
    deviation             INTEGER                                 NOT NULL,
    measurement_id        BIGINT                                  NOT NULL,
    result_measurement_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_controlPoint PRIMARY KEY (id),
    CONSTRAINT UQ_CONTROL_POINTS UNIQUE (survey_journal_id, equipment_id, place_number)
);

CREATE TABLE IF NOT EXISTS POINTS_DIFFERENCE
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id     BIGINT                                  NOT NULL,
    equipment_id          BIGINT                                  NOT NULL,
    type                  VARCHAR                                 NOT NULL,
    first_place_number    INTEGER                                 NOT NULL,
    second_place_number   INTEGER                                 NOT NULL,
    difference            INTEGER                                 NOT NULL,
    acceptable_deviation  BOOLEAN,
    result_measurement_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_pointDifference PRIMARY KEY (id),
    CONSTRAINT UQ_POINTS_DIFFERENCE UNIQUE (survey_journal_id, equipment_id, first_place_number, second_place_number)
);

CREATE TABLE IF NOT EXISTS DEFECT_MEASUREMENTS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id       BIGINT                                  NOT NULL,
    equipment_id            BIGINT                                  NOT NULL,
    element_id              BIGINT                                  NOT NULL,
    element_name            VARCHAR                                 NOT NULL,
    part_element_id         BIGINT,
    part_name               VARCHAR,
    defect_id               BIGINT                                  NOT NULL,
    defect_name             VARCHAR                                 NOT NULL,
    not_meet_requirements   BOOLEAN                                 NOT NULL,
    use_calculate_thickness BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_defectMeasurement PRIMARY KEY (id),
    CONSTRAINT UQ_DEFECT_MEASUREMENTS UNIQUE (survey_journal_id, element_id, part_element_id, defect_name)
);

CREATE TABLE IF NOT EXISTS COMPLETED_ELEMENT_REPAIRS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id BIGINT                                  NOT NULL,
    equipment_id      BIGINT                                  NOT NULL,
    element_id        BIGINT                                  NOT NULL,
    element_name      VARCHAR                                 NOT NULL,
    part_element_id   BIGINT,
    part_name         VARCHAR,
    repair_id         BIGINT                                  NOT NULL,
    repair_name       VARCHAR                                 NOT NULL,
    CONSTRAINT pk_completedRepairElement PRIMARY KEY (id),
    CONSTRAINT UQ_COMPLETED_ELEMENT_REPAIRS UNIQUE (survey_journal_id, element_id, part_element_id, repair_name)
);

CREATE TABLE IF NOT EXISTS CALCULATION_PARAMETERS
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    number                INTEGER,
    sequential_number     INTEGER,
    parameter_name        VARCHAR                                 NOT NULL,
    first_value           DOUBLE PRECISION                        NOT NULL,
    second_value          DOUBLE PRECISION,
    unit_measurement      VARCHAR                                 NOT NULL,
    defect_measurement_id BIGINT,
    completed_repair_id   BIGINT,
    CONSTRAINT pk_calculationParameterMeasurement PRIMARY KEY (id),
    CONSTRAINT FK_CALCULATION_PARAMETERS_ON_DEFECT_MEASUREMENTS
        FOREIGN KEY (defect_measurement_id) REFERENCES defect_measurements (id),
    CONSTRAINT FK_CALCULATION_PARAMETERS_ON_COMPLETED_ELEMENT_REPAIRS
        FOREIGN KEY (completed_repair_id) REFERENCES completed_element_repairs (id)
);

CREATE TABLE IF NOT EXISTS VISUAL_INSPECTIONS
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id BIGINT                                  NOT NULL,
    equipment_id      BIGINT                                  NOT NULL,
    element_id        BIGINT                                  NOT NULL,
    element_name      VARCHAR                                 NOT NULL,
    inspection        VARCHAR                                 NOT NULL,
    CONSTRAINT pk_visualInspection PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CALCULATING_UM_THICKNESS
(
    id                        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id         BIGINT                                  NOT NULL,
    equipment_id              BIGINT                                  NOT NULL,
    element_id                BIGINT                                  NOT NULL,
    element_name              VARCHAR                                 NOT NULL,
    part_element_id           BIGINT,
    part_name                 VARCHAR,
    diameter                  INTEGER,
    measurement_number        INTEGER,
    min_measurement_value     DOUBLE PRECISION                        NOT NULL,
    max_measurement_value     DOUBLE PRECISION                        NOT NULL,
    max_corrosion             DOUBLE PRECISION,
    residual_thickness        DOUBLE PRECISION                        NOT NULL,
    min_acceptable_value      DOUBLE PRECISION,
    acceptable_value          BOOLEAN,
    invalid_value             BOOLEAN,
    approaching_invalid_value BOOLEAN,
    reached_invalid_value     BOOLEAN,
    no_standard               BOOLEAN,
    CONSTRAINT pk_calculatingUltrasonicThicknessMeasurement PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ULTRASONIC_THICKNESS_MEASUREMENTS
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id     BIGINT                                  NOT NULL,
    equipment_id          BIGINT                                  NOT NULL,
    element_id            BIGINT                                  NOT NULL,
    part_element_id       BIGINT,
    diameter              INTEGER,
    measurement_number    INTEGER,
    min_measurement_value DOUBLE PRECISION                        NOT NULL,
    max_measurement_value DOUBLE PRECISION                        NOT NULL,
    CONSTRAINT pk_ultrasonicThicknessMeasurement PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS HARDNESS_MEASUREMENTS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    survey_journal_id  BIGINT                                  NOT NULL,
    equipment_id       BIGINT                                  NOT NULL,
    element_id         BIGINT                                  NOT NULL,
    element_name       VARCHAR                                 NOT NULL,
    part_element_id    BIGINT,
    part_name          VARCHAR,
    measurement_number INTEGER                                 NOT NULL,
    measurement_value  INTEGER                                 NOT NULL,
    diameter           INTEGER,
    acceptable_value   BOOLEAN,
    CONSTRAINT pk_hardnessMeasurement PRIMARY KEY (id)
);