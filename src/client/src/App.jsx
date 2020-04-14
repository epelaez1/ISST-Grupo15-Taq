import React, { Component } from 'react';
import { connect } from 'react-redux'
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import ErrorView from './views/ErrorView';
import Nav from "./components/Navbar"
import taquillas from './views/taquillas'
import home from './views/home';

class App extends Component {	
	render(){
	return (
		<div>
		<BrowserRouter>
			<div>
				<Nav />
				<Route exact path="/" component={home} />
				<Route path="/500" render={(props) => <ErrorView {...props} code={500} />} />
				<Route path="/taquillas" component={taquillas} />
				
			</div>
		</BrowserRouter>
		</div>
	);
}
}
function mapStateToProps(state){
    return{
        ...state
    };
    }
export default connect(mapStateToProps)(App);