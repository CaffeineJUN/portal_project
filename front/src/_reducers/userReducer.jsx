import React from 'react'
import * as types from '../_actions/types'

function userReducer(state = {}, action) {
    switch (action.type) {
        case types.REGIST_USER:
            return {
                ...state,
                registSuccess: action.payload,
            }

        case types.LOGIN_USER:
            return {
                ...state,
                loginSuccess: action.payload,
            }

        default:
            return state
    }
}

export default userReducer
