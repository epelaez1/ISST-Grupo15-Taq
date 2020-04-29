import {
	fetchGet, fetchPost,
	fetchDelete, fetchPut,
} from '../asyncRequests';

import {
	setLocker, setLockers,
	updateLocker as updateLockerAction,
	removeLocker as removeLockerAction,
} from '../../redux/actions/lockers';

import store from '../../redux/store';

const { dispatch } = store;

export const getAllLockers = () => fetchGet('/api/v1/admin/lockers')
	.then((r) => r.json())
	.then((lockers) => dispatch(setLockers(lockers)));

export const createLocker = (newLocker) => fetchPost('/api/v1/admin/locker', newLocker)
	.then((r) => r.json())
	.then((locker) => dispatch(setLocker(locker)));

export const updateLocker = (newLocker, oldLocker) => {
	const url = `/api/v1/admin/locker/${oldLocker.id}`;
	return fetchPut(url, newLocker)
		.then((r) => r.json())
		.then((locker) => dispatch(updateLockerAction(locker)));
};

export const removeLocker = (locker) => {
	const url = `/api/v1/admin/locker/${locker.id}`;
	return fetchDelete(url, {})
		.then((r) => r.json())
		.then(() => dispatch(removeLockerAction(locker)));
};
