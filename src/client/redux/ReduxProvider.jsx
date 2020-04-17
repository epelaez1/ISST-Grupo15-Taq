import { Provider } from 'react-redux';
import { createStore } from 'redux';
import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { createMuiTheme, ThemeProvider } from '@material-ui/core/styles';
import GlobalState from './reducers';
import TestView from '../views/TestView';
import Locations from '../views/Locations';
import ErrorView from '../views/ErrorView';
import Layout from '../components/Layout';
import SignIn from '../components/SignIn';

// import { questions } from "../assets/mock-data";

export default class ReduxProvider extends React.Component {
	constructor(props) {
		super(props);
		this.initialState = {
			pong: false,
			loggedUser: {},
			lockers: [],
			users: [],
			rentals: [],
			locations: [],
			payments: [],
			payment: [],
			lockerStates: [],
			rentalStates: [],
			paymentMethods: [],
		};
		this.store = this.configureStore();
	}

	configureStore() {
		return createStore(GlobalState, this.initialState);
	}

	render() {
		const theme = createMuiTheme({
			palette: {
				primary: {
					main: '#23395B',
				},
				secondary: {
					main: '#FB8B24',
				},
			},

		});
		return (
			<Provider store={this.store}>
				<BrowserRouter>
					<ThemeProvider theme={theme}>
						<Layout
							footer={(
								<div className="footer">
									<span>
										&copy; 2019-2020 &mdash; Delegación de Alumnos de Telecomunicación
									</span>
								</div>
							)}
						>
							<Switch>
								<Route exact path="/" component={TestView} />
								<Route exact path="/signin" component={SignIn} />
								<Route exact path="/admin/locations" component={Locations} />
								<Route path="/500" render={(props) => <ErrorView {...props} code={500} />} />
								<Route render={(props) => <ErrorView {...props} code={404} />} />
							</Switch>
						</Layout>
					</ThemeProvider>
				</BrowserRouter>
			</Provider>
		);
	}
}
