import React from 'react';
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  ImageBackground,
  SafeAreaView,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';

const WelcomePage = () => {
  const navigation = useNavigation();

  return (
    <SafeAreaView style={styles.container}>
      <ImageBackground
        source={require('../assets/Kigali.png')}
        resizeMode="cover"
        style={styles.background}
      >
        <View style={styles.overlay}>
          <Text style={styles.title}>Leadership Close to You</Text>
          <Text style={styles.subtitle}>Speak out and engage your community</Text>

          <TouchableOpacity
            style={styles.loginButton}
            onPress={() => navigation.navigate('Login')}
          >
            <Text style={styles.loginText}>Login</Text>
          </TouchableOpacity>

          <TouchableOpacity
            style={styles.guestButton}
            onPress={() => navigation.navigate('Guest')}
          >
            <Text style={styles.guestText}>Continue as Guest</Text>
          </TouchableOpacity>
        </View>
      </ImageBackground>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  background: {
    flex: 1,
    justifyContent: 'flex-end',
    width: '100%',
    height: '100%',
  },
  overlay: {
    padding: 24,
    alignItems: 'center',
    backgroundColor: 'rgba(0,0,0,0.6)',
    borderTopLeftRadius: 24,
    borderTopRightRadius: 24,
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#007AFF',
    marginBottom: 8,
  },
  subtitle: {
    fontSize: 14,
    textAlign: 'center',
    marginBottom: 24,
    color: '#fff',
  },
  loginButton: {
    backgroundColor: '#007AFF',
    paddingVertical: 12,
    paddingHorizontal: 32,
    borderRadius: 8,
    width: '100%',
    alignItems: 'center',
    marginBottom: 12,
  },
  loginText: {
    color: '#fff',
    fontSize: 16,
  },
  guestButton: {
    backgroundColor: '#333',
    paddingVertical: 12,
    paddingHorizontal: 32,
    borderRadius: 8,
    width: '100%',
    alignItems: 'center',
  },
  guestText: {
    color: '#fff',
    fontSize: 16,
  },
});

export default WelcomePage;