import {
	fetchGet, fetchPost,
	fetchDelete, fetchPut,
} from '../asyncRequests';

import {
	setPaymentMethod, setPaymentMethods,
	updatePaymentMethod as updatePaymentMethodAction,
	removePaymentMethod as removePaymentMethodAction,
} from '../../redux/actions/paymentMethods';

import store from '../../redux/store';

const { dispatch } = store;

export const getAllPaymentMethods = () => fetchGet('/api/v1/admin/paymentMethods')
	.then((r) => r.json())
	.then((paymentMethods) => dispatch(setPaymentMethods(paymentMethods)));

export const createPaymentMethod = (newPaymentMethod) => fetchPost('/api/v1/admin/paymentMethod', newPaymentMethod)
	.then((r) => r.json())
	.then((paymentMethod) => dispatch(setPaymentMethod(paymentMethod)));

export const updatePaymentMethod = (newPaymentMethod, oldPaymentMethod) => {
	const url = `/api/v1/admin/paymentMethod/${oldPaymentMethod.id}`;
	return fetchPut(url, newPaymentMethod)
		.then((r) => r.json())
		.then((paymentMethod) => dispatch(updatePaymentMethodAction(paymentMethod)));
};

export const removePaymentMethod = (paymentMethod) => {
	const url = `/api/v1/admin/paymentMethod/${paymentMethod.id}`;
	return fetchDelete(url, {})
		.then((r) => r.json())
		.then(() => dispatch(removePaymentMethodAction(paymentMethod)));
};
