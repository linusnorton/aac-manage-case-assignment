package uk.gov.hmcts.reform.managecase.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.managecase.domain.DynamicListElement;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.hmcts.reform.managecase.util.JacksonUtils.DL_ELEMENT_CODE;
import static uk.gov.hmcts.reform.managecase.util.JacksonUtils.DL_ELEMENT_LABEL;
import static uk.gov.hmcts.reform.managecase.util.JacksonUtils.DL_LIST_ITEMS;
import static uk.gov.hmcts.reform.managecase.util.JacksonUtils.DL_VALUE;

class JacksonUtilsTest {

    private JacksonUtils jacksonUtils;

    @Nested
    @DisplayName("JacksonUtils")
    class JacksonUtilsDynamicList {

        @BeforeEach
        void setUp() {
            jacksonUtils = new JacksonUtils(new ObjectMapper());
        }

        @Test
        @DisplayName("")
        public void createDynamicList() {

            DynamicListElement element1 = DynamicListElement.builder()
                .code("[Creator]")
                .label("Creator")
                .build();
            DynamicListElement element2 = DynamicListElement.builder()
                .code("[Debtor]")
                .label("Debtor")
                .build();
            DynamicListElement element3 = DynamicListElement.builder()
                .code("[Litigant]")
                .label("Litigant")
                .build();

            ObjectNode dynamicList = jacksonUtils.createDynamicList(
                element1,
                Arrays.asList(element1, element2, element3)
            );

            assertAll(
                () -> assertEquals(
                    "[Creator]", dynamicList.get(DL_VALUE).get(DL_ELEMENT_CODE).textValue()),
                () -> assertEquals(
                    "Creator", dynamicList.get(DL_VALUE).get(DL_ELEMENT_LABEL).textValue()),
                () -> assertEquals(
                    "[Creator]", dynamicList.get(DL_LIST_ITEMS).get(0).get(DL_ELEMENT_CODE).textValue()),
                () -> assertEquals(
                    "Creator", dynamicList.get(DL_LIST_ITEMS).get(0).get(DL_ELEMENT_LABEL).textValue()),
                () -> assertEquals(
                    "[Debtor]", dynamicList.get(DL_LIST_ITEMS).get(1).get(DL_ELEMENT_CODE).textValue()),
                () -> assertEquals(
                    "Debtor", dynamicList.get(DL_LIST_ITEMS).get(1).get(DL_ELEMENT_LABEL).textValue()),
                () -> assertEquals(
                    "[Litigant]", dynamicList.get(DL_LIST_ITEMS).get(2).get(DL_ELEMENT_CODE).textValue()),
                () -> assertEquals(
                    "Litigant", dynamicList.get(DL_LIST_ITEMS).get(2).get(DL_ELEMENT_LABEL).textValue())
            );
        }
    }
}
