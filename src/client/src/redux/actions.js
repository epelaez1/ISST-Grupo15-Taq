export const LOGIN = 'LOGIN';
export const USER_ANSWER = 'USER_ANSWER';
export const PASS_ANSWER = 'PASS_ANSWER';




export function login(username, password) {
    return { type: LOGIN, payload: { username, password } };
}

export function userAnswer(answer) {
    return { type: USER_ANSWER, payload: { answer } };
}
export function passAnswer(answer) {
    return { type: PASS_ANSWER, payload: { answer } };
}