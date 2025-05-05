import React, { useState } from 'react';
import axios from 'axios';
import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  StyleSheet,
  Image,
  CheckBox,
  Alert,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';
import Constants from 'expo-constants';

const LoginPage = () => {
  const [phoneNumber, setPhoneNumber] = useState('');
  const [password, setPassword] = useState('');
  const [useSMS, setUseSMS] = useState(false);
  const navigation = useNavigation();

  const BASE_URL = Constants.expoConfig.extra.API_BASE_URL;

  const handleLogin = async () => {
    try {
      const response = await axios.post(`${BASE_URL}/auth/login`, {
        phoneNumber,
        password,
      });

      const { token, role } = response.data;

      localStorage.setItem('userName', response.data.name);
      localStorage.setItem('userRole', response.data.role.name);
      localStorage.setItem('token', token);

      if (role === 'ADMIN') navigation.navigate('AdminDashboard');
      else navigation.navigate('UserDashboard');

    } catch (error) {
      Alert.alert('Login Failed', 'Invalid phone number or password.');
    }
  };

  return (
    <View style={styles.container}>
      <Image source={require('../assets/Kigali.png')} style={styles.backgroundImage} />
      <View style={styles.overlay}>
        <View style={styles.card}>
          <Image source={require('../assets/login-logo.png')} style={styles.logo} />
          <Text style={styles.welcomeText}>Welcome Back</Text>
          <TextInput
            style={styles.input}
            placeholder="Phone Number"
            value={phoneNumber}
            onChangeText={setPhoneNumber}
            keyboardType="phone-pad"
          />
          <TextInput
            style={styles.input}
            placeholder="Password"
            value={password}
            onChangeText={setPassword}
            secureTextEntry
          />
          <View style={styles.checkboxContainer}>
            <CheckBox value={useSMS} onValueChange={setUseSMS} />
            <Text style={styles.checkboxLabel}>Use SMS verification instead</Text>
          </View>
          <TouchableOpacity style={styles.loginButton} onPress={handleLogin}>
            <Text style={styles.loginButtonText}>Login</Text>
          </TouchableOpacity>
          <TouchableOpacity onPress={() => navigation.navigate('ForgotPassword')}>
            <Text style={styles.link}>Forgot Password?</Text>
          </TouchableOpacity>
          <Text style={styles.bottomText}>
            Don't have an account?{' '}
            <Text style={styles.link} onPress={() => navigation.navigate('Signup')}>
              Sign Up
            </Text>
          </Text>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  backgroundImage: {
    position: 'absolute',
    width: '100%',
    height: '100%',
    resizeMode: 'cover',
  },
  overlay: {
    flex: 1,
    backgroundColor: 'rgba(0,0,0,0.3)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  card: {
    backgroundColor: '#fff',
    borderRadius: 16,
    padding: 24,
    width: '85%',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.2,
    shadowRadius: 6,
    elevation: 10,
  },
  logo: {
    width: 60,
    height: 40,
    marginBottom: 16,
  },
  welcomeText: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  input: {
    width: '100%',
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 12,
    borderRadius: 8,
    marginBottom: 12,
    backgroundColor: '#fff',
  },
  checkboxContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    alignSelf: 'flex-start',
    marginBottom: 16,
  },
  checkboxLabel: {
    marginLeft: 8,
    fontSize: 14,
  },
  loginButton: {
    backgroundColor: '#007AFF',
    paddingVertical: 12,
    borderRadius: 8,
    width: '100%',
    alignItems: 'center',
    marginBottom: 16,
  },
  loginButtonText: {
    color: '#fff',
    fontWeight: 'bold',
    fontSize: 16,
  },
  link: {
    color: '#007AFF',
    fontSize: 14,
    marginTop: 8,
  },
  bottomText: {
    marginTop: 12,
    fontSize: 14,
    textAlign: 'center',
  },
});

export default LoginPage;