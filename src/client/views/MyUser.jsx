import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { scaffolds } from '../utils/tableScaffolds';
import { updateUser } from '../utils/api/users';
import getSession from '../utils/api/session';
import EditableTable from '../components/EditableTable';

const MyUser = () => {
	const usersScaffold = scaffolds.users;
	const loggedUser = useSelector((state) => state.loggedUser);
	useEffect(() => {
		getSession();
	}, []);
	return (
		<EditableTable
			data={[loggedUser]}
			columns={usersScaffold.columns}
			title={usersScaffold.title}
			create={() => {}}
			update={updateUser}
			remove={() => {}}
			actions={[]}
			editable
			showEmpty
		/>

	);
};

export default MyUser;
