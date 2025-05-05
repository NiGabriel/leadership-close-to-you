import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginPage from './screens/LoginPage';
import UserDashboard from './screens/UserDashboard';
import WelcomePage from './screens/WelcomePage';
import GuestDashboard from './screens/GuestDashboard';
// import AdminDashboard from './screens/AdminDashboard'; // placeholder
// import Signup from './screens/Signup'; // placeholder
// import ForgotPassword from './screens/ForgotPassword'; // placeholder

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Welcome">
        <Stack.Screen name="UserDashboard" component={UserDashboard} />
        <Stack.Screen name="Welcome" component={WelcomePage} options={{ headerShown: false }} />
        <Stack.Screen name="Login" component={LoginPage} options={{ headerShown: true }} />
        <Stack.Screen name="Guest" component={GuestDashboard} />
        {/* <Stack.Screen name="AdminDashboard" component={AdminDashboard} /> */}
        {/* <Stack.Screen name="Signup" component={Signup} /> */}
        {/* <Stack.Screen name="ForgotPassword" component={ForgotPassword} /> */}
      </Stack.Navigator>
    </NavigationContainer>
  );
}