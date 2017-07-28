package xyz.aungpyaephyo.ma.ac.events;


import java.util.List;

import xyz.aungpyaephyo.ma.ac.data.vo.AttractionVO;

/**
 * Created by aung on 7/9/16.
 */
public class DataEvent {

    public static class AttractionsLoadedEvent {

        private List<AttractionVO> attractionList;

        public AttractionsLoadedEvent(List<AttractionVO> attractionList) {
            this.attractionList = attractionList;
        }

        public List<AttractionVO> getAttractionList() {
            return attractionList;
        }
    }

    public static class AttractionsLoadedEmptyEvent {

    }

    public static class AttractionsLoadedErrorEvent {
        private String errorMessage;

        public AttractionsLoadedErrorEvent(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
