import { combineReducers } from 'redux';
import {LOGIN, USER_ANSWER, PASS_ANSWER} from "./actions";

function logged(state = false, action = {}) {
    switch (action.type) {
        case LOGIN:
            state=true;
        return state

        default: return state;
    }
}
function username(state = '', action = {}) {
    switch (action.type) {
        case USER_ANSWER:
            state=action.payload.answer;
        return state

        default: return state;
    }
}
function password(state = '', action = {}) {
    switch (action.type) {
        case PASS_ANSWER:
            state=action.payload.answer;
        return state

        default: return state;
    }
}

const GlobalState = (combineReducers({
   username,
   password,
   logged
}));

export default GlobalState;
