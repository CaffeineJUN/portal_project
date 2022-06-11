import React from 'react'
import * as types from '../_actions/types'

function diaryReducer(state = {}, action) {
    switch (action.type) {
        case types.CREATE_DIARY:
            return {
                ...state,
                diary: action.payload,
            }

        case types.SHOW_DIARY:
            return {
                ...state,
                diarys: action.payload,
            }

        default:
            return state
    }
}

export default diaryReducer
