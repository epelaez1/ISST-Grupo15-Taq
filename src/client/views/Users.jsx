import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { scaffolds } from '../utils/tableScaffolds';
import {
	getAllUsers, createUser,
	updateUser, removeUser,
} from '../utils/api/users';

import EditableTable from '../components/EditableTable';

const Users = () => {
	const usersScaffold = scaffolds.users;
	const admins = useSelector((state) => state.users.filter((user) => user.isAdmin));
	const allUsers = useSelector((state) => state.users);
	useEffect(() => {
		getAllUsers();
	}, []);
	return (
		<>
			<EditableTable
				data={admins}
				title="Administradores"
				columns={usersScaffold.columns}
				create={createUser}
				update={updateUser}
				remove={removeUser}
				actions={[]}
				editable
			/>
			<br />
			<EditableTable
				data={allUsers}
				title={usersScaffold.title}
				columns={usersScaffold.columns}
				create={createUser}
				update={updateUser}
				remove={removeUser}
				actions={[]}
				editable
				showEmpty

			/>
		</>
	);
};

export default Users;
