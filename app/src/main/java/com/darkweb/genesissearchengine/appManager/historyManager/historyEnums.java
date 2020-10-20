package com.darkweb.genesissearchengine.appManager.historyManager;

public class historyEnums
{
    /*History Manager*/
    public enum eHistoryViewCommands {
        M_UPDATE_LIST_IF_EMPTY, M_UPDATE_LIST, M_REMOVE_FROM_LIST, M_CLEAR_LIST,M_VERTIFY_SELECTION_MENU, M_CLOSE_MENU, M_HIDE_SEARCH, M_LONG_PRESS_MENU
    }

    public enum eHistoryAdapterCommands {
        M_ON_LOADING, M_LOADING_CLEAR, M_CLEAR_LONG_SELECTED_URL, GET_SELECTED_URL, GET_LONG_SELECTED_URL
    }

    public enum eHistoryViewAdapterCommands {
        M_OPEN_MENU, M_CLEAR_LONG_SELECTED_VIEW, M_SELECT_VIEW, M_CLEAR_HIGHLIGHT
    }

}