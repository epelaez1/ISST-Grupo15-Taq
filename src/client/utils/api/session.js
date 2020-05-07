import { fetchGet } from '../asyncRequests';

import { setSession } from '../../redux/actions/session';

import store from '../../redux/store';

const { dispatch } = store;

const getSession = () => fetchGet('/api/v1/app/session')
	.then((r) => r.json())
	.then((session) => dispatch(setSession(session)));

export default getSession;
