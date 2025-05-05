import React, { useEffect, useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
} from 'react-native';
import axios from 'axios';
import { Ionicons, MaterialIcons, Entypo } from '@expo/vector-icons';
import Constants from 'expo-constants';

const UserDashboard = () => {
  const BASE_URL = Constants.expoConfig?.extra?.API_BASE_URL;

  const [leaders, setLeaders] = useState([]);
  const [announcements, setAnnouncements] = useState([]);
  const [stats, setStats] = useState({ total: 0, resolved: 0, rate: '0%', avgTime: '0h' });
  const [greeting, setGreeting] = useState('');
  const [userName, setUserName] = useState('');

  useEffect(() => {
    const hour = new Date().getHours();
    if (hour < 12) setGreeting('Good morning');
    else if (hour < 18) setGreeting('Good afternoon');
    else setGreeting('Good evening');

    const storedName = localStorage.getItem('userName') || 'User';
    setUserName(storedName);

    const token = localStorage.getItem('token');
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };


    axios.get(`${BASE_URL}/leaders/nearby`, config)
      .then(res => setLeaders(res.data))
      .catch(console.error);

    axios.get(`${BASE_URL}/announcements/recent`, config)
      .then(res => setAnnouncements(res.data))
      .catch(console.error);

    axios.get(`${BASE_URL}/reports/stats`, config)
      .then(res => setStats(res.data))
      .catch(console.error);
  }, []);


  return (
    <ScrollView style={styles.container} contentContainerStyle={styles.contentContainer}>
      <View style={styles.header}>
        <Text style={styles.greeting}>{greeting}, {userName}</Text>
        <Text style={styles.location}>Kigali</Text>
      </View>

      <View style={styles.section}>
        <Text style={styles.sectionTitle}>Quick Actions</Text>
        <View style={styles.actionsGrid}>
          <TouchableOpacity style={styles.actionButton}>
            <Ionicons name="create-outline" size={24} color="#007AFF" />
            <Text style={styles.actionLabel}>Submit Report</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.actionButton}>
            <Ionicons name="person-outline" size={24} color="#007AFF" />
            <Text style={styles.actionLabel}>Find Leaders</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.actionButton}>
            <Ionicons name="document-text-outline" size={24} color="#007AFF" />
            <Text style={styles.actionLabel}>My Reports</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.actionButton}>
            <Ionicons name="notifications-outline" size={24} color="#007AFF" />
            <Text style={styles.actionLabel}>Notifications</Text>
          </TouchableOpacity>
        </View>
      </View>

      <View style={styles.section}>
        <View style={styles.sectionHeaderRow}>
          <Text style={styles.sectionTitle}>Nearby Leaders</Text>
          <Text style={styles.available}>{leaders.length} Available</Text>
        </View>
        {leaders.map((leader, index) => (
          <View style={styles.card} key={index}>
            <Text style={styles.cardName}>{leader.user.name}</Text>
            <Text style={styles.cardRole}>{leader.positionTitle}</Text>

            <View style={styles.iconRow}>
              {/* Call */}
              <TouchableOpacity onPress={() => Linking.openURL(`tel:${leader.officePhone}`)}>
                <MaterialIcons name="call" size={20} color="#007AFF" />
              </TouchableOpacity>

              {/* Message */}
              <TouchableOpacity onPress={() => Linking.openURL(`sms:${leader.officePhone}`)}>
                <MaterialIcons name="message" size={20} color="#007AFF" />
              </TouchableOpacity>

              {/* Email */}
              <TouchableOpacity onPress={() => Linking.openURL(`mailto:${leader.user.email}`)}>
                <Entypo name="mail" size={20} color="#007AFF" />
              </TouchableOpacity>
            </View>
          </View>
        ))}
      </View>

      <View style={styles.section}>
        <View style={styles.sectionHeaderRow}>
          <Text style={styles.sectionTitle}>Recent Announcements</Text>
          <TouchableOpacity>
            <Text style={styles.viewAll}>View All</Text>
          </TouchableOpacity>
        </View>
        {announcements.map((a, index) => (
          <View style={styles.cardAnnouncement} key={index}>
            <Text style={styles.tag}>{a.leader.user.name} • {a.createdAt}</Text>
            <Text style={styles.announcementTitle}>{a.title}</Text>
            <Text>{a.message}</Text>
          </View>
        ))}
      </View>

      <View style={styles.section}>
        <Text style={styles.sectionTitle}>Quick Stats</Text>
        <View style={styles.statsGrid}>
          <View style={styles.statCard}>
            <Text style={styles.statValue}>{stats.total}</Text>
            <Text style={styles.statLabel}>Total Reports</Text>
          </View>
          <View style={styles.statCard}>
            <Text style={styles.statValue}>{stats.resolved}</Text>
            <Text style={styles.statLabel}>Resolved Issues</Text>
          </View>
          <View style={styles.statCard}>
            <Text style={styles.statValue}>{stats.rate}</Text>
            <Text style={styles.statLabel}>Response Rate</Text>
          </View>
          <View style={styles.statCard}>
            <Text style={styles.statValue}>{stats.avgTime}</Text>
            <Text style={styles.statLabel}>Avg Response Time</Text>
          </View>
        </View>
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f8f9fb',
  },
  contentContainer: {
    padding: 16,
    paddingBottom: 24,
  },
  header: {
    backgroundColor: '#007AFF',
    borderRadius: 10,
    padding: 16,
    marginBottom: 16,
  },
  greeting: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
  },
  location: {
    color: '#fff',
    fontSize: 14,
  },
  section: {
    marginBottom: 24,
  },
  sectionHeaderRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 12,
  },
  sectionTitle: {
    fontSize: 16,
    fontWeight: '600',
  },
  available: {
    fontSize: 12,
    color: '#007AFF',
  },
  viewAll: {
    fontSize: 12,
    color: '#007AFF',
  },
  actionsGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  actionButton: {
    backgroundColor: '#fff',
    width: '48%',
    padding: 16,
    borderRadius: 12,
    marginBottom: 12,
    alignItems: 'center',
    shadowColor: '#ccc',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 3,
    elevation: 3,
  },
  actionLabel: {
    marginTop: 8,
    fontSize: 14,
  },
  card: {
    backgroundColor: '#fff',
    borderRadius: 12,
    padding: 16,
    shadowColor: '#ccc',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 3,
    elevation: 3,
    marginBottom: 10,
  },
  cardName: {
    fontWeight: 'bold',
    fontSize: 16,
  },
  cardRole: {
    fontSize: 14,
    marginBottom: 8,
  },
  iconRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '40%',
  },
  cardAnnouncement: {
    backgroundColor: '#fff',
    padding: 16,
    borderRadius: 12,
    marginBottom: 12,
  },
  tag: {
    color: '#007AFF',
    fontSize: 12,
    marginBottom: 4,
  },
  announcementTitle: {
    fontWeight: 'bold',
    fontSize: 15,
    marginBottom: 6,
  },
  statsGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  statCard: {
    width: '48%',
    backgroundColor: '#fff',
    borderRadius: 10,
    padding: 16,
    marginBottom: 12,
    alignItems: 'center',
  },
  statValue: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  statLabel: {
    fontSize: 13,
    color: '#777',
    marginTop: 4,
  },
});

export default UserDashboard;