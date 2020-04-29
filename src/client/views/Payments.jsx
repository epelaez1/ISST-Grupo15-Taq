import React, { useEffect } from 'react';
import { scaffolds } from '../utils/tableScaffolds';
import {
	getAllPaymentMethods, createPaymentMethod,
	updatePaymentMethod, removePaymentMethod,
} from '../utils/api/paymentMethods';

import EditableTable from '../components/EditableTable';

const Payments = () => {
	const paymentMethodsScaffold = scaffolds.paymentMethods;

	useEffect(() => {
		getAllPaymentMethods();
	}, []);

	return (
		<EditableTable
			model="paymentMethods"
			title={paymentMethodsScaffold.title}
			columns={paymentMethodsScaffold.columns}
			create={createPaymentMethod}
			update={updatePaymentMethod}
			remove={removePaymentMethod}
		/>

	);
};

export default Payments;
